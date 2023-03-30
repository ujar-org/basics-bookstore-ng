package org.ujar.bookstore.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ujar.bookstore.service.CheckoutService;
import org.ujar.bookstore.web.dto.Purchase;
import org.ujar.bookstore.web.dto.PurchaseResponse;

@RestController
@Tag(name = "Checkout", description = "API for checkout actions.")
@RequestMapping("/api/v1/checkout")
record CheckoutResource(CheckoutService checkoutService) {

  @PostMapping("/purchase")
  PurchaseResponse placeOrder(@RequestBody final Purchase purchase) {
    return checkoutService.placeOrder(purchase);
  }

}









