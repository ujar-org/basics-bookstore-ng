package dev.knowhowto.bookstore.repository;

import dev.knowhowto.bookstore.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
