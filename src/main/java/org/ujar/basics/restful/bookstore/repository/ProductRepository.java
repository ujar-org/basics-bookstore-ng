package org.ujar.basics.restful.bookstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.ujar.basics.restful.bookstore.entity.Product;
import org.ujar.basics.restful.bookstore.entity.ProductCategory;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaRepository<Product, Long> {
  Page<Product> findAllByCategory(ProductCategory category, Pageable pageable);
}
