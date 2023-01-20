package org.ujar.bs.rst.bookstore.web.dto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Set;
import org.ujar.bs.rst.bookstore.entity.Address;
import org.ujar.bs.rst.bookstore.entity.Customer;
import org.ujar.bs.rst.bookstore.entity.Order;
import org.ujar.bs.rst.bookstore.entity.OrderItem;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public record Purchase(Customer customer, Address shippingAddress, Address billingAddress,
                       Order order,
                       Set<OrderItem> orderItems) {
}
