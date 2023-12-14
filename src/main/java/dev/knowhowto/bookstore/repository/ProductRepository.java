package dev.knowhowto.bookstore.repository;

import dev.knowhowto.bookstore.entity.Product;
import dev.knowhowto.bookstore.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaRepository<Product, Long> {
  @Transactional(readOnly = true)
  Page<Product> findAllByCategory(ProductCategory category, Pageable pageable);

  @Transactional(readOnly = true)
  Page<Product> findByNameContaining(String name, Pageable pageable);
}
