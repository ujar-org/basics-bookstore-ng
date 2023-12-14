package dev.knowhowto.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

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
      sequenceName = "geo_state_id_seq",
      initialValue = 1,
      allocationSize = 1
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












