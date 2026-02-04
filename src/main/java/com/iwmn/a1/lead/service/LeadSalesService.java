package com.iwmn.a1.lead.service;

import com.iwmn.a1.lead.model.jpa.LeadSalesModel;

import java.util.Date;
import java.util.List;

public interface LeadSalesService {
    void save(LeadSalesModel model);

    List<LeadSalesModel> findAllByCreationDate(Date starDate, Date endDate);
}
