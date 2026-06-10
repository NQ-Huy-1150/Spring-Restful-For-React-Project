package com.react_project.backend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HousingExpenseRequest {

    @NotNull(message = "Month must not be null")
    LocalDate month;

    @PositiveOrZero(message = "House price must be greater than or equal to 0")
    Double housePrice;

    @PositiveOrZero(message = "Amount of electric must be greater than or equal to 0")
    Double amoutOfElectric;

    @PositiveOrZero(message = "Electricity price must be greater than or equal to 0")
    Double electricityPrice;
//    Double electricityBill;

    @PositiveOrZero(message = "Amount of water must be greater than or equal to 0")
    Double amoutOfWater;

    @PositiveOrZero(message = "Water price must be greater than or equal to 0")
    Double waterPrice;
//    Double waterBill;

    @PositiveOrZero(message = "Service costs must be greater than or equal to 0")
    Double serviceCosts;

    @PositiveOrZero(message = "Other costs must be greater than or equal to 0")
    Double othercosts;


}
