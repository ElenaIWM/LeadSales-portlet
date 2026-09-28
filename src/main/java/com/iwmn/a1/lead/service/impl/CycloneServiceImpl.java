package com.iwmn.a1.lead.service.impl;

import com.iwmn.a1.lead.repository.CycloneRepository;
import com.iwmn.a1.lead.service.CycloneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("!mock")
@Service
public class CycloneServiceImpl implements CycloneService {

    @Autowired
    CycloneRepository repository;

    @Override
    public String createLeadCyclone(String phone, String city, String firstName, String lastName, String ban, String source, String installationCity, String streetId, String addressEntryId, String addressApartmentId, String additionalInfo) {
        return repository.createLeadCyclone(phone, city, firstName, lastName, ban, source, installationCity, streetId, addressEntryId, addressApartmentId, additionalInfo);
    }
}
