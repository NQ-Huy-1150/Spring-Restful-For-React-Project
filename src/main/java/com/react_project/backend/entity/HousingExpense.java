package com.react_project.backend.entity;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

}
