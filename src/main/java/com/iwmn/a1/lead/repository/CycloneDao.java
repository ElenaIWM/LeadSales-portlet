package com.iwmn.a1.lead.repository;

public interface CycloneDao {

    String createLeadCyclone(String phone, String city, String firstName, String lastName, String ban, String source, String installationCity, String streetId, String addressEntryId, String addressApartmentId, String additionalInfo);
}
