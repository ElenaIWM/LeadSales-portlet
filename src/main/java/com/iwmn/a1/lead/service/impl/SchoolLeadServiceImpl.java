package com.iwmn.a1.lead.service.impl;

import com.iwmn.a1.lead.model.jpa.SchoolLeadModel;
import com.iwmn.a1.lead.repository.jpa.SchoolLeadRepository;
import com.iwmn.a1.lead.service.SchoolLeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SchoolLeadServiceImpl implements SchoolLeadService {

    @Autowired
    SchoolLeadRepository repository;

    @Override
    public void save(SchoolLeadModel model) {
        repository.save(model);
    }

    @Override
    public List<SchoolLeadModel> findAllByCreationDate(Date starDate, Date endDate) {
        return repository.findAllByCreationDateBetween(starDate, endDate);
    }
}
