package com.iwmn.a1.lead.service.impl;

import com.iwmn.a1.lead.model.jpa.LeadSalesModel;
import com.iwmn.a1.lead.repository.jpa.LeadSalesRepository;
import com.iwmn.a1.lead.service.LeadSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LeadSalesServiceImpl implements LeadSalesService {

    @Autowired
    LeadSalesRepository repository;

    @Override
    public void save(LeadSalesModel model) {
        repository.save(model);
    }

    @Override
    public List<LeadSalesModel> findAllByCreationDate(Date starDate, Date endDate) {
        return repository.findAllByCreationDateBetween(starDate, endDate);
    }
}
