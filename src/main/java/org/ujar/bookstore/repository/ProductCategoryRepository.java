package org.ujar.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.bookstore.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
