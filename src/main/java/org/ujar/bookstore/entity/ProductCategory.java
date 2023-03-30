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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = ProductCategory.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory {

  protected static final String TABLE_NAME = "bookstore_categories";

  @Id
  @SequenceGenerator(
      name = "product_category_id_seq",
      sequenceName = "product_category_id_seq"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "product_category_id_seq"
  )
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
  @JsonIgnore
  private Set<Product> products = new HashSet<>();

  @Override
  public String toString() {
    return "ProductCategory{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", products=" + products +
           '}';
  }
}
