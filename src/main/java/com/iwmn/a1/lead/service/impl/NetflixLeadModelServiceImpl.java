package com.iwmn.a1.lead.service.impl;

import com.iwmn.a1.lead.model.jpa.NetflixLeadModel;
import com.iwmn.a1.lead.repository.jpa.NetflixLeadRepository;
import com.iwmn.a1.lead.service.NetflixLeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NetflixLeadModelServiceImpl implements NetflixLeadService {

    @Autowired
    NetflixLeadRepository repository;


    @Override
    public void save(NetflixLeadModel model) {
        repository.save(model);
    }

    @Override
    public List<NetflixLeadModel> findAllByCreationDate(Date starDate, Date endDate) {
        return repository.findAllByCreationDateBetween(starDate, endDate);
    }
}
