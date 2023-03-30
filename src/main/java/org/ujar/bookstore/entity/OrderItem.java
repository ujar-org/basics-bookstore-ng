package org.ujar.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = OrderItem.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

  protected static final String TABLE_NAME = "checkout_order_items";

  @Id
  @SequenceGenerator(
      name = "checkout_order_item_id_seq",
      sequenceName = "checkout_order_item_id_seq"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "checkout_order_item_id_seq"
  )
  @Column(name = "id")
  private Long id;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "unit_price")
  private BigDecimal unitPrice;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "product_id")
  private Long productId;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @Override
  public String toString() {
    return "OrderItem{" +
           "id=" + id +
           ", imageUrl='" + imageUrl + '\'' +
           ", unitPrice=" + unitPrice +
           ", quantity=" + quantity +
           ", productId=" + productId +
           ", order=" + order +
           '}';
  }
}








