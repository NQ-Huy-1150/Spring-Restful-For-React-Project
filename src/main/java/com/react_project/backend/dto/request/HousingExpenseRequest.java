package com.react_project.backend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HousingExpenseRequest {

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


}
