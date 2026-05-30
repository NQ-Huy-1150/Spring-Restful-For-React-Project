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

    LocalDate month;

    Double amoutOfElectric;
    Double electricityBill;

    Double amoutOfWater;
    Double waterBill;

    Double othercosts;

    Double total;



}
