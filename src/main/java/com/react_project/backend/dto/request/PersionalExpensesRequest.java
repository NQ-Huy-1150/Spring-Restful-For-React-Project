package com.react_project.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class PersionalExpensesRequest {

    //
    LocalDate month;

    Double totalIncome;

    Double houseCost;

    Double foodCost;

    Double traveCost;

    Double otherCost1;

    Double otherCost2;

    Double otherCost3;

    Double savingAndInvestment;

    Double RemaningAmount;
}
