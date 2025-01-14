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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = ProductCategory.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {

  protected static final String TABLE_NAME = "bookstore_categories";

  @Id
  @SequenceGenerator(
      name = "product_category_id_seq",
      sequenceName = "product_category_id_seq",
      initialValue = 1,
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "product_category_id_seq"
  )
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
  @JsonIgnore
  private Set<Product> products = new HashSet<>();

  @Override
  public String toString() {
    return "ProductCategory{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", products=" + products +
           '}';
  }
}
