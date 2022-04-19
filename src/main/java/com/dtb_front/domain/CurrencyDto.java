package com.dtb_front.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CurrencyDto {
    private Long id;
    private String fromCurrency;
    private String toCurrency;
    private double rate;
    private double amount;
    private double convertedAmount;
    private LocalDateTime created;
}
