package com.react_project.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HousingExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    LocalDate month;

    Double amoutOfElectric;
    Double electricityBill;

    Double amoutOfWater;
    Double waterBill;

    Double othercosts;

    Double total;



}
