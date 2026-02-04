package com.iwmn.a1.lead.service;

import com.iwmn.a1.lead.model.jpa.LeadSalesModel;
import com.iwmn.a1.lead.model.jpa.SchoolLeadModel;

import java.io.File;

public interface EmailService {
    void sendLeadForm(LeadSalesModel model, String emailTo);
    void sendLeadFormToCustomer(String emailTo, String languageId);

    void sendNetflixLeadReport(File attachment);

    void sendSchoolLeadForm(SchoolLeadModel model);

    void sendInternetAvailabilityReport(File attachFilePrivate, File attachFileBusiness);
}
