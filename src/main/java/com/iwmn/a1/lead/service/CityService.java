package com.iwmn.a1.lead.service;

import com.iwmn.a1.lead.model.jpa.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    List<City> findAllCitiesOrderByWeight();
    Optional<City> getCityById(Long id);
}
