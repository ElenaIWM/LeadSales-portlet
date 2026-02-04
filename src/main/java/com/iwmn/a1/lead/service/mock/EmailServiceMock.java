package com.iwmn.a1.lead.service.mock;

import com.iwmn.a1.lead.model.jpa.LeadSalesModel;
import com.iwmn.a1.lead.model.jpa.SchoolLeadModel;
import com.iwmn.a1.lead.service.EmailService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;

@Profile({"mock", "vpnlocal"})
@Service
public class EmailServiceMock implements EmailService {

    @Override
    public void sendLeadForm(LeadSalesModel model, String emailTo) {
        System.out.println("sendLeadForm");
    }

    @Override
    public void sendLeadFormToCustomer(String emailTo, String languageId) {
        System.out.println("sendLeadFormToCustomer");
    }

    @Override
    public void sendNetflixLeadReport(File attachment) {
        System.out.println("sendNetflixLeadReport");
    }

    @Override
    public void sendSchoolLeadForm(SchoolLeadModel model) {
        System.out.println("sendSchoolLeadForm");
    }

    @Override
    public void sendInternetAvailabilityReport(File attachFilePrivate, File attachFileBusiness) {
        System.out.println("sendInternetAvailabilityReport");
    }
}
