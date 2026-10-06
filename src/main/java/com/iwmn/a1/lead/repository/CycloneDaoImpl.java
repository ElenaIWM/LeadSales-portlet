package com.iwmn.a1.lead.repository;

import com.iwmn.CycloneSession;
import com.sugarcrm.www.sugarcrm.Installation_address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Profile("!mock")
@Service
public class CycloneDaoImpl implements CycloneDao{

    @Autowired
    private CycloneSession cycloneSession;

    @Override
    public String createLeadCyclone(String phone, String city, String firstName, String lastName, String ban, String source, String installationCity, String streetId, String addressEntryId, String addressApartmentId, String additionalInfo) {
        System.out.println("CycloneDaoImpl.createLeadCyclone request: phone=" + phone + ", city=" + city
                + ", firstName=" + firstName + ", lastName=" + lastName + ", ban=" + ban + ", source=" + source
                + ", installationCity=" + installationCity + ", streetId=" + streetId
                + ", addressEntryId=" + addressEntryId + ", addressApartmentId=" + addressApartmentId
                + ", additionalInfo=" + additionalInfo);
        Installation_address installationAddress = new Installation_address();
        try {
            installationAddress.setCity(installationCity);
            installationAddress.setId_street(streetId);
            installationAddress.setId_address_entry(addressEntryId);
            installationAddress.setId_address_appartment(addressApartmentId);

            String res = cycloneSession.createLeadCyclone(phone, city, firstName, lastName, ban, source, installationAddress, additionalInfo);

            System.out.println("CycloneDaoImpl.createLeadCyclone response: " + res);
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
