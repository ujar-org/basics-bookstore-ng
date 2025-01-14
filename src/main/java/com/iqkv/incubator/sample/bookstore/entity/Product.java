/*
 * Copyright 2024 IQKV Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iqkv.incubator.sample.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Setter
@Table(name = Product.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  protected static final String TABLE_NAME = "bookstore_products";

  @Id
  @SequenceGenerator(
      name = "product_id_seq",
      sequenceName = "product_id_seq",
      initialValue = 1,
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "product_id_seq"
  )
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private ProductCategory category;

  @Column(name = "sku")
  private String sku;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "unit_price")
  private BigDecimal unitPrice;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "active")
  private boolean active;

  @Column(name = "units_in_stock")
  private int unitsInStock;

  @CreatedDate
  @Column(name = "created_at")
  @JsonProperty("created_at")
  private Instant createdAt;

  @Override
  public String toString() {
    return "Product{" +
           "id=" + id +
           ", category=" + category +
           ", sku='" + sku + '\'' +
           ", name='" + name + '\'' +
           ", description='" + description + '\'' +
           ", unitPrice=" + unitPrice +
           ", imageUrl='" + imageUrl + '\'' +
           ", active=" + active +
           ", unitsInStock=" + unitsInStock +
           ", createdAt=" + createdAt +
           '}';
  }
}
