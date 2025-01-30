/*
 * Copyright 2025 IQKV Team.
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = Order.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  protected static final String TABLE_NAME = "checkout_orders";

  @Id
  @SequenceGenerator(
      name = "checkout_order_id_seq",
      sequenceName = "checkout_order_id_seq",
      initialValue = 1,
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "checkout_order_id_seq"
  )
  @Column(name = "id")
  private Long id;

  @Column(name = "order_tracking_number")
  private String orderTrackingNumber;

  @Column(name = "total_quantity")
  private int totalQuantity;

  @Column(name = "total_price")
  private BigDecimal totalPrice;

  @Column(name = "status")
  private String status;

  @Column(name = "date_created")
  @CreationTimestamp
  private Date dateCreated;

  @Column(name = "last_updated")
  @UpdateTimestamp
  private Date lastUpdated;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
  private Set<OrderItem> orderItems = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
  private Address shippingAddress;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
  private Address billingAddress;

  public void add(OrderItem item) {

    if (item != null) {
      if (orderItems == null) {
        orderItems = new HashSet<>();
      }

      orderItems.add(item);
      item.setOrder(this);
    }
  }

  @Override
  public String toString() {
    return "Order{" +
           "id=" + id +
           ", orderTrackingNumber='" + orderTrackingNumber + '\'' +
           ", totalQuantity=" + totalQuantity +
           ", totalPrice=" + totalPrice +
           ", status='" + status + '\'' +
           ", dateCreated=" + dateCreated +
           ", lastUpdated=" + lastUpdated +
           ", orderItems=" + orderItems +
           ", customer=" + customer +
           ", shippingAddress=" + shippingAddress +
           ", billingAddress=" + billingAddress +
           '}';
  }
}









