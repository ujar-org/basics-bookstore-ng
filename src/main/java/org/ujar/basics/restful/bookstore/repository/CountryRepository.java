package org.ujar.basics.restful.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.basics.restful.bookstore.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
