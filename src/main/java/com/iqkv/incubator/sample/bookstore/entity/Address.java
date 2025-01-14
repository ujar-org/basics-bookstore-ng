/*
 * Copyright 2024 IQKV Team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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





