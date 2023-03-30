package org.ujar.bookstore.web.dto;

import java.util.Set;

import org.ujar.bookstore.entity.Address;
import org.ujar.bookstore.entity.Customer;
import org.ujar.bookstore.entity.Order;
import org.ujar.bookstore.entity.OrderItem;

public record Purchase(Customer customer, Address shippingAddress, Address billingAddress,
                       Order order,
                       Set<OrderItem> orderItems) {
}
