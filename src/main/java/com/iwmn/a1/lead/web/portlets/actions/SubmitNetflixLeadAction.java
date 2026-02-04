package com.iwmn.a1.lead.web.portlets.actions;

import com.iwmn.a1.lead.model.jpa.NetflixLeadModel;
import com.iwmn.a1.lead.service.NetflixLeadService;
import com.iwmn.a1.lead.service.SchoolLeadService;
import com.iwmn.a1.lead.web.portlets.Constants;
import com.iwmn.a1.lead.web.portlets.LeadUtils;
import com.iwmn.a1.lead.web.portlets.actions.model.ViewModel;
import com.iwmn.a1.lead.web.portlets.api.Action;
import com.iwmn.a1.lead.web.portlets.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.io.IOException;
import java.util.Date;

@Component(SubmitNetflixLeadAction.ACTION_NAME)
public class SubmitNetflixLeadAction implements Action {
    public static final String ACTION_NAME = "submitNetflixLeadAction";

    @Autowired
    NetflixLeadService service;

    @Override
    public ViewModel execute(PortletRequest request, PortletResponse response) throws IOException, PortletException {
        ViewModel model = new ViewModel(Constants.Pages.NETFLIX_LEAD_PAGE, Constants.Modules.NETFLIX_LEAD);
        System.out.println("submitNetflixLeadAction");
        String city = RequestUtil.getParam(request, Constants.Params.CITY);
        String msisdn = RequestUtil.getParam(request, Constants.Params.MSISDN);
        String currentUrl = RequestUtil.getParam(request, Constants.Params.CURRENT_URL);
        String website = RequestUtil.getParam(request, Constants.Params.WEBSITE);

        if((website == null || website.trim().isEmpty()) && city!=null && !city.isEmpty()
            && msisdn!=null && !msisdn.isEmpty() && msisdn.startsWith("07") && msisdn.length() == 9
            && currentUrl!=null && !currentUrl.isEmpty()){
            Date now = new Date();

            NetflixLeadModel netflixLeadModel = new NetflixLeadModel();
            netflixLeadModel.setCity(city);
            netflixLeadModel.setPhoneNumber(msisdn);
            netflixLeadModel.setUrl(currentUrl);
            netflixLeadModel.setCreationDate(now);
            netflixLeadModel.setIp(LeadUtils.getIpAddress(request));

            service.save(netflixLeadModel);

            request.setAttribute("status", Constants.MESSAGES.LEAD_FORM_SUCCESS);
        }else{
            request.setAttribute("error", Constants.MESSAGES.LEAD_FORM_ERROR);
        }
        return model;
    }
}
