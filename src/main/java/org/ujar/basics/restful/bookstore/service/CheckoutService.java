package org.ujar.basics.restful.bookstore.service;

import java.util.Set;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ujar.basics.restful.bookstore.web.dto.Purchase;
import org.ujar.basics.restful.bookstore.web.dto.PurchaseResponse;
import org.ujar.basics.restful.bookstore.entity.Customer;
import org.ujar.basics.restful.bookstore.entity.Order;
import org.ujar.basics.restful.bookstore.entity.OrderItem;
import org.ujar.basics.restful.bookstore.repository.CustomerRepository;

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









