package com.iwmn.a1.lead.service.impl;

import com.iwmn.a1.lead.model.jpa.InternetAvailabilityModel;
import com.iwmn.a1.lead.repository.jpa.InternetAvailabilityRepository;
import com.iwmn.a1.lead.repository.jpa.LeadSalesRepository;
import com.iwmn.a1.lead.service.InternetAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InternetAvailabilityServiceImpl implements InternetAvailabilityService {

    @Autowired
    InternetAvailabilityRepository repository;

    @Override
    public void save(InternetAvailabilityModel model) {
        repository.save(model);
    }

    @Override
    public List<InternetAvailabilityModel> findAllByCreationDate(Date starDate, Date endDate) {
        return repository.findAllByCreationDateBetween(starDate, endDate);
    }
}
