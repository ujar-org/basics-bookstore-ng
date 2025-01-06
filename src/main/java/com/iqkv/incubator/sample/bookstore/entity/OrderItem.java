/*
 * Copyright 2024 IQKV.
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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = OrderItem.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

  protected static final String TABLE_NAME = "checkout_order_items";

  @Id
  @SequenceGenerator(
      name = "checkout_order_item_id_seq",
      sequenceName = "checkout_order_item_id_seq",
      initialValue = 1,
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "checkout_order_item_id_seq"
  )
  @Column(name = "id")
  private Long id;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "unit_price")
  private BigDecimal unitPrice;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "product_id")
  private Long productId;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @Override
  public String toString() {
    return "OrderItem{" +
           "id=" + id +
           ", imageUrl='" + imageUrl + '\'' +
           ", unitPrice=" + unitPrice +
           ", quantity=" + quantity +
           ", productId=" + productId +
           ", order=" + order +
           '}';
  }
}








