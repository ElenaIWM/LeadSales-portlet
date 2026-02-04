package com.iwmn.a1.lead.service;


import com.iwmn.a1.lead.model.jpa.SchoolLeadModel;

import java.util.Date;
import java.util.List;

public interface SchoolLeadService {
    void save(SchoolLeadModel model);

    List<SchoolLeadModel> findAllByCreationDate(Date starDate, Date endDate);
}
