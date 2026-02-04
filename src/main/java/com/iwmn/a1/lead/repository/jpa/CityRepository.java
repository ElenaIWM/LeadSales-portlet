package com.iwmn.a1.lead.repository.jpa;

import com.iwmn.a1.lead.model.jpa.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findAllByOrderByWeightAsc();
    Optional<City> findById(Long id);
}
