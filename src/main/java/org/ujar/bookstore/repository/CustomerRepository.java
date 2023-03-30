package org.ujar.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.bookstore.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
