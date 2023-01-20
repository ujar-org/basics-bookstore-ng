package org.ujar.bs.rst.bookstore.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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








