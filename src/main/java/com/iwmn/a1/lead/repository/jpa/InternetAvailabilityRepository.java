package com.iwmn.a1.lead.repository.jpa;

import com.iwmn.a1.lead.model.jpa.InternetAvailabilityModel;
import com.iwmn.a1.lead.model.jpa.SchoolLeadModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface InternetAvailabilityRepository extends JpaRepository<InternetAvailabilityModel, Long> {
    List<InternetAvailabilityModel> findAllByCreationDateBetween(Date startDate, Date endDate);
}
