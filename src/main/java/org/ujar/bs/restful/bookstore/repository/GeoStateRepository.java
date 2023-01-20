package org.ujar.bs.rst.bookstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.bs.rst.bookstore.entity.GeoCountry;
import org.ujar.bs.rst.bookstore.entity.GeoState;

public interface GeoStateRepository extends JpaRepository<GeoState, Long> {
  List<GeoState> findAllByCountry(GeoCountry geoCountry);
}
