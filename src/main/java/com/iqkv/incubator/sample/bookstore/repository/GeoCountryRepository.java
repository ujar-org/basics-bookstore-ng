package com.iqkv.incubator.sample.bookstore.repository;

import com.iqkv.incubator.sample.bookstore.entity.GeoCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoCountryRepository extends JpaRepository<GeoCountry, Long> {

}
