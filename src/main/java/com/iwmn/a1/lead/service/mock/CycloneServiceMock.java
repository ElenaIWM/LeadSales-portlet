package com.iwmn.a1.lead.service.mock;

import com.iwmn.a1.lead.service.CycloneService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("mock")
@Service
public class CycloneServiceMock implements CycloneService {
    @Override
    public String createLeadCyclone(String phone, String city, String firstName, String lastName, String ban, String source, String installationCity, String streetId, String addressEntryId, String addressApartmentId, String additionalInfo) {
        return "Success";
    }
}
