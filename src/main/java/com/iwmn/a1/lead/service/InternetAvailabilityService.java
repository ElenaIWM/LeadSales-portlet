package com.iwmn.a1.lead.service;

import com.iwmn.a1.lead.model.jpa.InternetAvailabilityModel;

import java.util.Date;
import java.util.List;

public interface InternetAvailabilityService {
    void save(InternetAvailabilityModel model);

    List<InternetAvailabilityModel> findAllByCreationDate(Date starDate, Date endDate);
}
