package org.ujar.bs.rst.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.bs.rst.bookstore.entity.GeoCountry;

public interface GeoCountryRepository extends JpaRepository<GeoCountry, Long> {

}
