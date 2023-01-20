package org.ujar.bs.rst.bookstore.entity;

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
@Table(name = GeoState.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeoState {

  protected static final String TABLE_NAME = "geo_states";

  @Id
  @SequenceGenerator(
      name = "geo_state_id_seq",
      sequenceName = "geo_state_id_seq"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "geo_state_id_seq"
  )
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "country_id")
  private GeoCountry country;

  @Override
  public String toString() {
    return "GeoState{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", country=" + country +
           '}';
  }
}












