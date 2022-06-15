package org.ujar.basics.restful.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.basics.restful.bookstore.entity.GeoCountry;

public interface GeoCountryRepository extends JpaRepository<GeoCountry, Long> {

}
