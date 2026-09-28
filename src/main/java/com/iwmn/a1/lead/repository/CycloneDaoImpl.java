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
//        System.out.println("phone " + phone);
//        System.out.println("city " + city);
//        System.out.println("firstName " + firstName);
//        System.out.println("lastName " + lastName);
//        System.out.println("ban " + ban);
//        System.out.println("source " + source);
//        System.out.println("installationCity " + installationCity);
//        System.out.println("streetId " + streetId);
//        System.out.println("addressEntryId " + addressEntryId);
//        System.out.println("addressApartmentId " + addressApartmentId);
//        System.out.println("additionalInfo " + additionalInfo);
        Installation_address installationAddress = new Installation_address();
        try {
            installationAddress.setCity(installationCity);
            installationAddress.setId_street(streetId);
            installationAddress.setId_address_entry(addressEntryId);
            installationAddress.setId_address_appartment(addressApartmentId);

            String res = cycloneSession.createLeadCyclone(phone, city, firstName, lastName, ban, source, installationAddress, additionalInfo);

//            System.out.println("res " + res);
            return res;
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
