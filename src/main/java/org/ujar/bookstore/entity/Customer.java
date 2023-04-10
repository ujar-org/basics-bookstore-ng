package org.ujar.bookstore.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Customer.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  protected static final String TABLE_NAME = "checkout_customers";

  @Id
  @SequenceGenerator(
      name = "checkout_customer_id_seq",
      sequenceName = "checkout_customer_id_seq",
      initialValue = 1,
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "checkout_customer_id_seq"
  )
  @Column(name = "id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private Set<Order> orders = new HashSet<>();

  public void add(Order order) {

    if (order != null) {

      if (orders == null) {
        orders = new HashSet<>();
      }

      orders.add(order);
      order.setCustomer(this);
    }
  }

  @Override
  public String toString() {
    return "Customer{" +
           "id=" + id +
           ", firstName='" + firstName + '\'' +
           ", lastName='" + lastName + '\'' +
           ", email='" + email + '\'' +
           ", orders=" + orders +
           '}';
  }
}









