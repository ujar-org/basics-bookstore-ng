package org.ujar.basics.restful.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.basics.restful.bookstore.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
