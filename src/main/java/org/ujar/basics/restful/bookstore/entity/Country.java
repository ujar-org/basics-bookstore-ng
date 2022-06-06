package org.ujar.basics.restful.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = Country.TABLE_NAME)
@Getter
@Setter
public class Country {

  protected static final String TABLE_NAME = "geo_countries";

  @Id
  @SequenceGenerator(
      name = "geo_country_id_seq",
      sequenceName = "geo_country_id_seq"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "geo_country_id_seq"
  )
  @Column(name = "id")
  private int id;

  @Column(name = "code")
  private String code;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "country")
  @JsonIgnore
  private List<State> states;

}










