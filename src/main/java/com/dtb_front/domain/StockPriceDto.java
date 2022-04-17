package com.dtb_front.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vaadin.flow.data.binder.PropertyId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockPriceDto {
    @PropertyId("shortName")
    private String shortName;
    @PropertyId("currency")
    private String currency;
    @PropertyId("exchangeName")
    private String exchangeName;
    @PropertyId("symbol")
    private String symbol;
    @PropertyId("price")
    private double price;
}
