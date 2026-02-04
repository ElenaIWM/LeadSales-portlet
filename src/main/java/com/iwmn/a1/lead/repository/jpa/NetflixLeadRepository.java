package com.iwmn.a1.lead.repository.jpa;

import com.iwmn.a1.lead.model.jpa.NetflixLeadModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface NetflixLeadRepository  extends JpaRepository<NetflixLeadModel, Long> {
    List<NetflixLeadModel> findAllByCreationDateBetween(Date startDate, Date endDate);
}
