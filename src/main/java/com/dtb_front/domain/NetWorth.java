package com.dtb_front.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NetWorth {
    private Long id;
    private double realEstate;
    private double cash;
    private double vehicles;
    private double savingsAndInvestments;
    private double foreignCurrencies;
    private double stocks;
    private double collections;
    private double homeContent;
    private double otherAssets;
    private double mortgage;
    private double loans;
    private double creditCards;
    private double otherLiabilities;
    private double totalAssets;
    private double totalLiabilities;
    private double totalNetWorth;
    private LocalDate date;
    private User user;
}
