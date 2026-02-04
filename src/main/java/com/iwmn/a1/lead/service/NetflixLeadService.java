package com.iwmn.a1.lead.service;

import com.iwmn.a1.lead.model.jpa.NetflixLeadModel;

import java.util.Date;
import java.util.List;

public interface NetflixLeadService {
    void save(NetflixLeadModel model);

    List<NetflixLeadModel> findAllByCreationDate(Date starDate, Date endDate);
}
