package org.ujar.basics.restful.bookstore.dto;

import java.util.Set;
import org.ujar.basics.restful.bookstore.entity.Address;
import org.ujar.basics.restful.bookstore.entity.Customer;
import org.ujar.basics.restful.bookstore.entity.Order;
import org.ujar.basics.restful.bookstore.entity.OrderItem;

public record Purchase(Customer customer, Address shippingAddress, Address billingAddress, Order order,
                       Set<OrderItem> orderItems) {
}
