package com.iqkv.incubator.sample.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = GeoCountry.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeoCountry {

  protected static final String TABLE_NAME = "geo_countries";

  @Id
  @SequenceGenerator(
      name = "geo_country_id_seq",
      sequenceName = "geo_country_id_seq",
      initialValue = 1,
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "geo_country_id_seq"
  )
  @Column(name = "id")
  private Long id;

  @Column(name = "code")
  private String code;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "country")
  @JsonIgnore
  private List<GeoState> geoStates = new ArrayList<>();

  @Override
  public String toString() {
    return "GeoCountry{" +
           "id=" + id +
           ", code='" + code + '\'' +
           ", name='" + name + '\'' +
           ", geoStates=" + geoStates +
           '}';
  }
}










