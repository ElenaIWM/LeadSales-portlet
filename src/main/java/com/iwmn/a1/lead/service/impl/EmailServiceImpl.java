package com.iwmn.a1.lead.service.impl;

import com.iwmn.a1.lead.model.jpa.LeadSalesModel;
import com.iwmn.a1.lead.model.jpa.SchoolLeadModel;
import com.iwmn.a1.lead.repository.EmailRepository;
import com.iwmn.a1.lead.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;

@Profile({"prod", "preprod"})
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Override
    public void sendLeadForm(LeadSalesModel model, String emailTo) {
        emailRepository.sendLeadForm(model, emailTo);
    }

    @Override
    public void sendLeadFormToCustomer(String emailTo, String languageId) {
        emailRepository.sendLeadFormToCustomer(emailTo, languageId);
    }

    @Override
    public void sendNetflixLeadReport(File attachment) {
        emailRepository.sendNetflixLeadReport(attachment);
    }

    @Override
    public void sendSchoolLeadForm(SchoolLeadModel model) {
        emailRepository.sendSchoolLeadForm(model);
    }

    @Override
    public void sendInternetAvailabilityReport(File attachFilePrivate, File attachFileBusiness) {
        emailRepository.sendInternetAvailabilityReport(attachFilePrivate, attachFileBusiness);
    }
}
