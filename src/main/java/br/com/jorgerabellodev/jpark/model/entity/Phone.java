package br.com.jorgerabellodev.jpark.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "phone")
@AllArgsConstructor
public class Phone {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_seq")
  @SequenceGenerator(name = "phone_seq", sequenceName = "phone_sequence", allocationSize = 1)
  private Long id;

  private String countryCode;

  private String ddd;

  private String number;
}

