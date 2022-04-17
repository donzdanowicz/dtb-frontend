package com.dtb_front.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StockPrice {
    private String shortName;
    private String currency;
    private String exchangeName;
    private String symbol;
    private double price;
}