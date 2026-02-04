package com.iwmn.a1.lead.web.portlets.actions;

import com.iwmn.a1.lead.model.jpa.SchoolLeadModel;
import com.iwmn.a1.lead.service.EmailService;
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

@Component(SubmitSchoolLeadAction.ACTION_NAME)
public class SubmitSchoolLeadAction implements Action {
    public static final String ACTION_NAME = "submitSchoolLeadAction";

    @Autowired
    SchoolLeadService service;

    @Autowired
    EmailService emailService;

    @Override
    public ViewModel execute(PortletRequest request, PortletResponse response) throws IOException, PortletException {
        ViewModel model = new ViewModel(Constants.Pages.SCHOOL_LEAD_PAGE, Constants.Modules.SCHOOL_LEAD);
        System.out.println("submitSchoolLeadAction");
        String schoolName = RequestUtil.getParam(request, Constants.Params.SCHOOL_NAME);
        String city = RequestUtil.getParam(request, Constants.Params.CITY);
        String contactPerson = RequestUtil.getParam(request, Constants.Params.CONTACT_PERSON);
        String msisdn = RequestUtil.getParam(request, Constants.Params.MSISDN);
        String email = RequestUtil.getParam(request, Constants.Params.EMAIL);
        String personType = RequestUtil.getParam(request, Constants.Params.PERSON_TYPE);
        String website = RequestUtil.getParam(request, Constants.Params.WEBSITE);

        if((website == null || website.trim().isEmpty()) && schoolName!=null && !schoolName.isEmpty()
            && city!=null && !city.isEmpty()
            && contactPerson!=null && !contactPerson.isEmpty()
            && msisdn!=null && !msisdn.isEmpty() && msisdn.startsWith("07") && msisdn.length() == 9
            && email!=null && !email.isEmpty()
            && personType!=null && !personType.isEmpty()){

            Date now = new Date();
            SchoolLeadModel schoolLeadModel = new SchoolLeadModel();
            schoolLeadModel.setSchoolName(schoolName);
            schoolLeadModel.setCity(city);
            schoolLeadModel.setContactPerson(contactPerson);
            schoolLeadModel.setPhoneNumber(msisdn);
            schoolLeadModel.setEmail(email);
            schoolLeadModel.setPersonType(personType);
            schoolLeadModel.setCreationDate(now);
            schoolLeadModel.setIp(LeadUtils.getIpAddress(request));

            emailService.sendSchoolLeadForm(schoolLeadModel);
            service.save(schoolLeadModel);
            request.setAttribute("status", Constants.MESSAGES.LEAD_FORM_SUCCESS);
        }else{
            request.setAttribute("error", Constants.MESSAGES.LEAD_FORM_ERROR);
        }
        return model;
    }
}
