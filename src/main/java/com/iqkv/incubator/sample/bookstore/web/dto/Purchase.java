package com.iqkv.incubator.sample.bookstore.web.dto;

import java.util.Set;

import com.iqkv.incubator.sample.bookstore.entity.Address;
import com.iqkv.incubator.sample.bookstore.entity.Customer;
import com.iqkv.incubator.sample.bookstore.entity.Order;
import com.iqkv.incubator.sample.bookstore.entity.OrderItem;

public record Purchase(Customer customer, Address shippingAddress, Address billingAddress,
                       Order order,
                       Set<OrderItem> orderItems) {
}
