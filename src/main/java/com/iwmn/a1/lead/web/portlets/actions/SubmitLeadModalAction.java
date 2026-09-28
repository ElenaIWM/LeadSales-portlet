package com.iwmn.a1.lead.web.portlets.actions;

import com.iwmn.a1.lead.model.jpa.LeadSalesModel;
import com.iwmn.a1.lead.service.CycloneService;
import com.iwmn.a1.lead.service.EmailService;
import com.iwmn.a1.lead.service.LeadSalesService;
import com.iwmn.a1.lead.web.portlets.Constants;
import com.iwmn.a1.lead.web.portlets.LeadUtils;
import com.iwmn.a1.lead.web.portlets.actions.model.ViewModel;
import com.iwmn.a1.lead.web.portlets.api.Action;
import com.iwmn.a1.lead.web.portlets.utils.RequestUtil;
import com.iwmn.utils.MsisdnUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.sugarcrm.www.sugarcrm.SugarsoapBindingStub;
import com.sugarcrm.www.sugarcrm.SugarsoapLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;

import static com.iwmn.a1.lead.web.portlets.Constants.PHONE_NUMBER_REGEX;

@Component(SubmitLeadModalAction.ACTION_NAME)
public class SubmitLeadModalAction implements Action {

    public static final String ACTION_NAME = "submitLeadModalAction";

    @Autowired
    LeadSalesService leadSalesService;

    @Autowired
    EmailService emailService;

    @Value("${internet.availability.form.sugar.endpoint}")
    public String internetAvailabilitySugarEndpoint;

    @Autowired
    private CycloneService cycloneService;

    @Override
    public ViewModel execute(PortletRequest request, PortletResponse response) throws IOException, PortletException {
        ViewModel model = new ViewModel(Constants.Pages.LEAD_MODAL_PAGE, Constants.Modules.LEAD_MODAL);
        System.out.println("submitLeadModalAction");
        String fullName = RequestUtil.getParam(request, Constants.Params.FULL_NAME);
        String companyName = RequestUtil.getParam(request, Constants.Params.COMPANY_NAME);
        String phoneNumber = RequestUtil.getParam(request, Constants.Params.PHONE_NUMBER);
        String email = RequestUtil.getParam(request, Constants.Params.EMAIL);
        String installationAddress = RequestUtil.getParam(request, Constants.Params.INSTALLATION_ADDRESS);
        String comment = RequestUtil.getParam(request, Constants.Params.COMMENT);
        String currentUrl = RequestUtil.getParam(request, Constants.Params.CURRENT_URL);
        String showSurvey = RequestUtil.getParam(request, Constants.Params.SHOW_SURVEY);
        String website = RequestUtil.getParam(request, Constants.Params.WEBSITE);

        if((website == null || website.trim().isEmpty()) && fullName!=null && !fullName.equals("") &&
                phoneNumber!=null && !phoneNumber.equals("") && isValidPhone(phoneNumber) &&
                email!=null && !email.equals("") &&
                currentUrl!=null && !currentUrl.equals("")){
            LeadSalesModel leadSalesModel = new LeadSalesModel();
            leadSalesModel.setFullName(fullName);
            leadSalesModel.setCompanyName(companyName);
            leadSalesModel.setPhoneNumber(phoneNumber);
            leadSalesModel.setEmail(email);
            leadSalesModel.setInstalationAddress(installationAddress);
            leadSalesModel.setFormComment(comment);
            leadSalesModel.setUrl(currentUrl);
            leadSalesModel.setIp(LeadUtils.getIpAddress(request));

            PortletPreferences prefs = request.getPreferences();

            String sendCyclone = prefs.getValue(Constants.SEND_CYCLONE, "false");
            if(sendCyclone!=null && sendCyclone.equals("sendCyclone")){
                if(internetAvailabilitySugarEndpoint!=null && !internetAvailabilitySugarEndpoint.isEmpty()){

                        String source = "WEB B2C";
                        if(currentUrl!=null && currentUrl.contains("/delovni")){
                            source = "WEB B2B";
                        }
                        String cycloneResponse = cycloneService.createLeadCyclone(MsisdnUtil.normalizeMsisdn(phoneNumber), "","", "", "", source, "", "", "", "", currentUrl);
                        if(cycloneResponse!=null && (cycloneResponse.equals("Success - Cyclone created") || cycloneResponse.equals("Success - Cyclone updated"))) {
                            String leadEmail = "";
                            String leadCategory = prefs.getValue("leadCategory", "1");
                            if(leadCategory==null || leadCategory.equals(Constants.LEAD_CARE)){
                                leadSalesModel.setLeadType(Constants.LEAD_CARE_STRING);
                                leadEmail = Constants.LEAD_CARE_EMAIL_TO;
                            }else if(leadCategory.equals(Constants.LEAD_SALES)){
                                leadSalesModel.setLeadType(Constants.LEAD_SALES_STRING);
                                leadEmail = Constants.LEAD_SALES_EMAIL_TO;
                            }else{
                                leadSalesModel.setLeadType(Constants.LEAD_OTHER_STRING);
                                leadEmail = prefs.getValue("leadEmail", Constants.LEAD_SALES_EMAIL_TO);
                            }
                            leadSalesModel.setEmailTo(leadEmail);

                            Date now = new Date();
                            leadSalesModel.setCreationDate(now);
                            //database column limit 255
                            try {
                                leadSalesService.save(leadSalesModel);
                            }catch(Exception e) {
                                currentUrl = currentUrl.substring(0,250);
                                leadSalesModel.setUrl(currentUrl);
                                leadSalesService.save(leadSalesModel);
                            }
                            request.setAttribute("showSurvey", showSurvey);
                            request.setAttribute("msisdn", phoneNumber);
                            request.setAttribute("email", email);
                            request.setAttribute("success", Constants.MESSAGES.LEAD_FORM_SUCCESS);
                        }else {
                            request.setAttribute("error", Constants.MESSAGES.LEAD_FORM_ERROR);
                        }

                }else{
                    request.setAttribute("error", Constants.MESSAGES.LEAD_FORM_ERROR);
                }
            }else{
                String leadEmail = "";
                String leadCategory = prefs.getValue("leadCategory", "1");
                if(leadCategory==null || leadCategory.equals(Constants.LEAD_CARE)){
                    leadSalesModel.setLeadType(Constants.LEAD_CARE_STRING);
                    leadEmail = Constants.LEAD_CARE_EMAIL_TO;
                }else if(leadCategory.equals(Constants.LEAD_SALES)){
                    leadSalesModel.setLeadType(Constants.LEAD_SALES_STRING);
                    leadEmail = Constants.LEAD_SALES_EMAIL_TO;
                }else{
                    leadSalesModel.setLeadType(Constants.LEAD_OTHER_STRING);
                    leadEmail = prefs.getValue("leadEmail", Constants.LEAD_SALES_EMAIL_TO);
                }
                leadEmail+=",elinda.stojanovamilosheska@a1.mk";
                leadSalesModel.setEmailTo(leadEmail);
                emailService.sendLeadForm(leadSalesModel, leadEmail);

                Date now = new Date();
                leadSalesModel.setCreationDate(now);
                //database column limit 255
                try {
                    leadSalesService.save(leadSalesModel);
                }catch(Exception e) {
                    currentUrl = currentUrl.substring(0,250);
                    leadSalesModel.setUrl(currentUrl);
                    leadSalesService.save(leadSalesModel);
                }

                String languageId = LanguageUtil.getLanguageId(request);
                emailService.sendLeadFormToCustomer(leadSalesModel.getEmailTo(), languageId);

                request.setAttribute("showSurvey", showSurvey);
                request.setAttribute("msisdn", phoneNumber);
                request.setAttribute("email", email);
                request.setAttribute("status", Constants.MESSAGES.LEAD_FORM_SUCCESS);
            }
        }else{
            request.setAttribute("error", Constants.MESSAGES.LEAD_FORM_ERROR);
        }
        return model;
    }

    public Boolean isValidPhone(String phone) {
        Matcher matcher = PHONE_NUMBER_REGEX.matcher(phone);
        return matcher.find();
    }
}
