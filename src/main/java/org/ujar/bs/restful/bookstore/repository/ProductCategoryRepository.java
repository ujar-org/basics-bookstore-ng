package org.ujar.bs.rst.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ujar.bs.rst.bookstore.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
