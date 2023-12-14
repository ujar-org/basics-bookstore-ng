package dev.knowhowto.bookstore.web.dto;

import java.util.Set;

import dev.knowhowto.bookstore.entity.Address;
import dev.knowhowto.bookstore.entity.Customer;
import dev.knowhowto.bookstore.entity.Order;
import dev.knowhowto.bookstore.entity.OrderItem;

public record Purchase(Customer customer, Address shippingAddress, Address billingAddress,
                       Order order,
                       Set<OrderItem> orderItems) {
}
