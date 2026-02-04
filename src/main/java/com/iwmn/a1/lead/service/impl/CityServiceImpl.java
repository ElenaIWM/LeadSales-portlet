package com.iwmn.a1.lead.service.impl;

import com.iwmn.a1.lead.model.jpa.City;
import com.iwmn.a1.lead.repository.jpa.CityRepository;
import com.iwmn.a1.lead.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepository repository;

//    public CityServiceImpl(CityRepository repository){ this.repository = repository; }

    @Override
    public List<City> findAllCitiesOrderByWeight() {
        return this.repository.findAllByOrderByWeightAsc();
    }

    @Override
    public Optional<City> getCityById(Long id) {
        return this.repository.findById(id);
    }
}
