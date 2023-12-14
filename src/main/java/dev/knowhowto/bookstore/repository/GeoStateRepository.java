package dev.knowhowto.bookstore.repository;

import java.util.List;

import dev.knowhowto.bookstore.entity.GeoCountry;
import dev.knowhowto.bookstore.entity.GeoState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoStateRepository extends JpaRepository<GeoState, Long> {
  List<GeoState> findAllByCountry(GeoCountry geoCountry);
}
