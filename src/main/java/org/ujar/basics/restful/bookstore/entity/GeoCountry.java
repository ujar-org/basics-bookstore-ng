package org.ujar.basics.restful.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
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
      sequenceName = "geo_country_id_seq"
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










