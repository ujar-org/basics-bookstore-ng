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

package com.iqkv.incubator.sample.bookstore.service;

import jakarta.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

import com.iqkv.incubator.sample.bookstore.entity.Customer;
import com.iqkv.incubator.sample.bookstore.entity.Order;
import com.iqkv.incubator.sample.bookstore.entity.OrderItem;
import com.iqkv.incubator.sample.bookstore.repository.CustomerRepository;
import com.iqkv.incubator.sample.bookstore.web.dto.Purchase;
import com.iqkv.incubator.sample.bookstore.web.dto.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckoutService {

  private final CustomerRepository customerRepository;

  @Transactional
  public PurchaseResponse placeOrder(Purchase purchase) {

    // retrieve the order info from dto
    Order order = purchase.order();

    // generate tracking number
    String orderTrackingNumber = generateOrderTrackingNumber();
    order.setOrderTrackingNumber(orderTrackingNumber);

    // populate order with orderItems
    Set<OrderItem> orderItems = purchase.orderItems();
    orderItems.forEach(order::add);

    // populate order with billingAddress and shippingAddress
    order.setBillingAddress(purchase.billingAddress());
    order.setShippingAddress(purchase.shippingAddress());

    // populate customer with order
    Customer customer = purchase.customer();
    customer.add(order);

    // save to the database
    customerRepository.save(customer);

    // return a response
    return new PurchaseResponse(orderTrackingNumber);
  }

  private String generateOrderTrackingNumber() {

    // generate a random UUID number (UUID version-4)
    // For details see: https://en.wikipedia.org/wiki/Universally_unique_identifier
    //
    return UUID.randomUUID().toString();
  }
}









