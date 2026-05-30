package com.react_project.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// Tính tiền sinh hoạt phí,lấy tổng thu nhập trừ tiền nhà
// trừ tiền ăn trừ tiền đi lại, trừ 3 chi phí khác nếu có
// trừ tiền tiết kiệm và đầu tư, và tính toán còn lại bao nhiêu
public class PersionalExpenses {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
}
