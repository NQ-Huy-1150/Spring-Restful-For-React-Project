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

// Tính tiền phòng: Nhập số điện, giá điện
public class HousingExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    LocalDate month;

    Double housePrice;

    Double amoutOfElectric;
    Double electricityPrice;
    Double electricityBill;

    Double amoutOfWater;
    Double waterPrice;
    Double waterBill;

    Double serviceCosts;

    Double othercosts;

    Double total;

}
