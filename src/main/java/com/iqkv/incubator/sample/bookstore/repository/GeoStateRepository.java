package com.iqkv.incubator.sample.bookstore.repository;

import java.util.List;

import com.iqkv.incubator.sample.bookstore.entity.GeoCountry;
import com.iqkv.incubator.sample.bookstore.entity.GeoState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoStateRepository extends JpaRepository<GeoState, Long> {
  List<GeoState> findAllByCountry(GeoCountry geoCountry);
}
