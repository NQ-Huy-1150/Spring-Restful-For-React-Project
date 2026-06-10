package com.react_project.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class PersionalExpensesRequest {
    @NotNull(message = "Month must not be null")
    LocalDate month;

    @PositiveOrZero(message = "Total income must be greater than or equal to 0")
    Double totalIncome;

    @PositiveOrZero(message = "House cost must be greater than or equal to 0")
    Double houseCost;

    @PositiveOrZero(message = "Food cost must be greater than or equal to 0")
    Double foodCost;

    @PositiveOrZero(message = "Travel cost must be greater than or equal to 0")
    Double traveCost;

    @PositiveOrZero(message = "Other cost 1 must be greater than or equal to 0")
    Double otherCost1;

    @PositiveOrZero(message = "Other cost 2 must be greater than or equal to 0")
    Double otherCost2;

    @PositiveOrZero(message = "Other cost 3 must be greater than or equal to 0")
    Double otherCost3;

    @PositiveOrZero(message = "Saving and investment must be greater than or equal to 0")
    Double savingAndInvestment;
//
//    Double RemaningAmount;
}
