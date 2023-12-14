package dev.knowhowto.bookstore.service;

import jakarta.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

import dev.knowhowto.bookstore.entity.Customer;
import dev.knowhowto.bookstore.entity.Order;
import dev.knowhowto.bookstore.entity.OrderItem;
import dev.knowhowto.bookstore.repository.CustomerRepository;
import dev.knowhowto.bookstore.web.dto.Purchase;
import dev.knowhowto.bookstore.web.dto.PurchaseResponse;
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









