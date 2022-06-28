package org.ujar.basics.restful.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
  private Set<Product> products;

  @Override
  public String toString() {
    return "ProductCategory{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", products=" + products +
           '}';
  }
}
