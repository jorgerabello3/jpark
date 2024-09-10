package br.com.jorgerabellodev.jpark.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
  @SequenceGenerator(name = "company_seq", sequenceName = "company_sequence", allocationSize = 1)
  private Long id;

  private String name;

  @Column(unique = true)
  private String cnpj;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "phone_id", referencedColumnName = "id")
  private Phone phone;

  private int carParkingSpot;

  private int motorCycleParkingSpot;

}
