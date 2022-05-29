package org.ujar.basics.restful.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.basics.restful.bookstore.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
