package org.ujar.bs.rst.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.bs.rst.bookstore.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
