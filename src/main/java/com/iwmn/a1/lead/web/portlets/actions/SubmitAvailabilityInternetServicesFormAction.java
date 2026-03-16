package com.iwmn.a1.lead.web.portlets.actions;

import com.iwmn.a1.lead.model.jpa.InternetAvailabilityModel;
import com.iwmn.a1.lead.service.InternetAvailabilityService;
import com.iwmn.a1.lead.web.portlets.Constants;
import com.iwmn.a1.lead.web.portlets.LeadUtils;
import com.iwmn.a1.lead.web.portlets.actions.model.ViewModel;
import com.iwmn.a1.lead.web.portlets.api.Action;
import com.iwmn.a1.lead.web.portlets.utils.RequestUtil;
import com.iwmn.a1.lead.web.portlets.utils.TransliterateUtil;
import com.iwmn.utils.MsisdnUtil;
import com.sugarcrm.www.sugarcrm.SugarsoapBindingStub;
import com.sugarcrm.www.sugarcrm.SugarsoapLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;
import java.util.Date;

@Component(SubmitAvailabilityInternetServicesFormAction.ACTION_NAME)
public class SubmitAvailabilityInternetServicesFormAction implements Action {

    public static final String ACTION_NAME = "submitAvailabilityInternetServicesFormAction";

    @Autowired
    InternetAvailabilityService internetAvailabilityService;

    @Value("${internet.availability.form.sugar.endpoint}")
    public String internetAvailabilitySugarEndpoint;

    @Override
    public ViewModel execute(PortletRequest request, PortletResponse response) throws IOException, PortletException {
        ViewModel model = new ViewModel(Constants.Pages.AVAILABILITY_INTERNET_SERVICES_PAGE, Constants.Modules.AVAILABILITY_INTERNET_SERVICES_FORM);
        System.out.println("submitAvailabilityInternetServicesFormAction");
        System.out.println("internetAvailabilitySugarEndpoint " + internetAvailabilitySugarEndpoint);

        try{
            String userType = RequestUtil.getParam(request, Constants.Params.USER_TYPE);
            String city = RequestUtil.getParam(request, Constants.Params.CITY);
            String cityCyrilic = RequestUtil.getParam(request, Constants.Params.CITY_CYRILIC);
            String address = RequestUtil.getParam(request, Constants.Params.ADDRESS);
            String msisdn = RequestUtil.getParam(request, Constants.Params.MSISDN);
            String currentUrl = RequestUtil.getParam(request, Constants.Params.CURRENT_URL);
            String addressLatin = TransliterateUtil.cyrillicToLatin(address);
            String website = RequestUtil.getParam(request, Constants.Params.WEBSITE);

            if((website == null || website.trim().isEmpty()) && city!=null && !city.isEmpty()
//					&& address!=null && !address.isEmpty()
                    && msisdn!=null && !msisdn.isEmpty() && msisdn.startsWith("07") && msisdn.length() == 9) {

                Date now = new Date();
//		        System.out.println("userType " + userType);
                InternetAvailabilityModel internetAvailabilityModel = new InternetAvailabilityModel();
                internetAvailabilityModel.setCity(city);
                if(address!=null && !address.isEmpty()){
                    internetAvailabilityModel.setAddress(address);
                }
                internetAvailabilityModel.setAddressLatin(addressLatin);
                internetAvailabilityModel.setMsisdn(msisdn);
                internetAvailabilityModel.setUrl(currentUrl);
                internetAvailabilityModel.setCreationDate(now);
                internetAvailabilityModel.setUserType(userType);
                internetAvailabilityModel.setIp(LeadUtils.getIpAddress(request));
                internetAvailabilityService.save(internetAvailabilityModel);

                if(internetAvailabilitySugarEndpoint!=null && !internetAvailabilitySugarEndpoint.isEmpty()){
                    SugarsoapLocator locator = new SugarsoapLocator();
                    locator.setsugarsoapPortEndpointAddress(internetAvailabilitySugarEndpoint);

                    SugarsoapBindingStub proxy = (SugarsoapBindingStub) locator.getsugarsoapPort();
                    String source = "WEB B2C";
                    if(currentUrl!=null && currentUrl.contains("/delovni")){
                        source = "WEB B2B";
                    }
                    String responseCreateLeadCyclone = proxy.createLeadCyclone(MsisdnUtil.normalizeMsisdn(msisdn), cityCyrilic, "", "", "", source);
                    System.out.println("responseCreateLeadCyclone " + responseCreateLeadCyclone);
                    if(responseCreateLeadCyclone!=null && (responseCreateLeadCyclone.equals("Success - Cyclone created") || responseCreateLeadCyclone.equals("Success - Cyclone updated"))) {
                        request.setAttribute("success", true);
                    }else {
                        request.setAttribute("error", true);
                    }
                }
            }else {
                request.setAttribute("error", true);
            }
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("error", true);
        }
        return model;
    }
}
