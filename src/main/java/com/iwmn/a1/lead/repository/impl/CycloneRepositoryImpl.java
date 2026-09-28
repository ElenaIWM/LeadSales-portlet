package com.iwmn.a1.lead.repository.impl;

import com.iwmn.a1.lead.repository.CycloneDao;
import com.iwmn.a1.lead.repository.CycloneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("!mock")
@Repository
public class CycloneRepositoryImpl implements CycloneRepository {
    @Autowired
    CycloneDao cycloneDao;

    @Override
    public String createLeadCyclone(String phone, String city, String firstName, String lastName, String ban, String source, String installationCity, String streetId, String addressEntryId, String addressApartmentId, String additionalInfo) {
        return cycloneDao.createLeadCyclone(phone, city, firstName, lastName, ban, source, installationCity, streetId, addressEntryId, addressApartmentId,additionalInfo);
    }
}
