package org.ujar.basics.restful.bookstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.basics.restful.bookstore.entity.GeoCountry;
import org.ujar.basics.restful.bookstore.entity.GeoState;

public interface GeoStateRepository extends JpaRepository<GeoState, Long> {
  List<GeoState> findAllByCountry(GeoCountry geoCountry);
}
