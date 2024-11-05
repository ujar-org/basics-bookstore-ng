package com.iqkv.incubator.sample.bookstore.repository;

import com.iqkv.incubator.sample.bookstore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
