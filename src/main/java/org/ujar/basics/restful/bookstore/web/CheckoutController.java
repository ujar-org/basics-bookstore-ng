package org.ujar.basics.restful.bookstore.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ujar.basics.restful.bookstore.service.CheckoutService;
import org.ujar.basics.restful.bookstore.web.dto.Purchase;
import org.ujar.basics.restful.bookstore.web.dto.PurchaseResponse;

@RestController
@RequestMapping("/api/v1/checkout")
@RequiredArgsConstructor
public class CheckoutController {

  private final CheckoutService checkoutService;

  @PostMapping("/purchase")
  public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
    return checkoutService.placeOrder(purchase);
  }

}









