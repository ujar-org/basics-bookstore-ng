package org.ujar.basics.restful.bookstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.basics.restful.bookstore.entity.Country;
import org.ujar.basics.restful.bookstore.entity.State;

public interface StateRepository extends JpaRepository<State, Long> {
  List<State> findAllByCountry(Country country);
}
