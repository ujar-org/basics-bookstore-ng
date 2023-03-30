package org.ujar.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.ujar.bookstore.entity.Product;
import org.ujar.bookstore.entity.ProductCategory;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaRepository<Product, Long> {
  @Transactional(readOnly = true)
  Page<Product> findAllByCategory(ProductCategory category, Pageable pageable);

  @Transactional(readOnly = true)
  Page<Product> findByNameContaining(String name, Pageable pageable);
}
