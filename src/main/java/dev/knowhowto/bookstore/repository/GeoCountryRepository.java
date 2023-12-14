package dev.knowhowto.bookstore.repository;

import dev.knowhowto.bookstore.entity.GeoCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoCountryRepository extends JpaRepository<GeoCountry, Long> {

}
