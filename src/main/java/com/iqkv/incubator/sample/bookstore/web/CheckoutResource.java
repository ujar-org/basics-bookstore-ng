package com.iqkv.incubator.sample.bookstore.web;

import com.iqkv.incubator.sample.bookstore.service.CheckoutService;
import com.iqkv.incubator.sample.bookstore.web.dto.Purchase;
import com.iqkv.incubator.sample.bookstore.web.dto.PurchaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Checkout", description = "API for checkout actions.")
@RequestMapping("/api/v1/checkout")
record CheckoutResource(CheckoutService checkoutService) {

  @PostMapping("/purchase")
  PurchaseResponse placeOrder(@RequestBody final Purchase purchase) {
    return checkoutService.placeOrder(purchase);
  }

}









