package com.iqkv.incubator.sample.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Address.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

  protected static final String TABLE_NAME = "checkout_address";

  @Id
  @SequenceGenerator(
      name = "checkout_address_id_seq",
      sequenceName = "checkout_address_id_seq",
      initialValue = 1,
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "checkout_address_id_seq"
  )
  @Column(name = "id")
  private Long id;

  @Column(name = "street")
  private String street;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

  @Column(name = "country")
  private String country;

  @Column(name = "zip_code")
  private String zipCode;

  @OneToOne
  @PrimaryKeyJoinColumn
  private Order order;

  @Override
  public String toString() {
    return "Address{" +
           "id=" + id +
           ", street='" + street + '\'' +
           ", city='" + city + '\'' +
           ", state='" + state + '\'' +
           ", country='" + country + '\'' +
           ", zipCode='" + zipCode + '\'' +
           ", order=" + order +
           '}';
  }
}





