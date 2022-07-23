package org.ujar.basics.restful.bookstore.web.dto;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Set;
import org.ujar.basics.restful.bookstore.entity.Address;
import org.ujar.basics.restful.bookstore.entity.Customer;
import org.ujar.basics.restful.bookstore.entity.Order;
import org.ujar.basics.restful.bookstore.entity.OrderItem;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public record Purchase(Customer customer, Address shippingAddress, Address billingAddress,
                       Order order,
                       Set<OrderItem> orderItems) {
}
