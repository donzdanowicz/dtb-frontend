package com.dtb_front.mapper;

import com.dtb_front.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PriceMapper {
    public StockPrice mapToPrice(final StockPriceDto stockPriceDto) {
        return StockPrice.builder()
                .shortName(stockPriceDto.getShortName())
                .currency(stockPriceDto.getCurrency())
                .exchangeName(stockPriceDto.getExchangeName())
                .symbol(stockPriceDto.getSymbol())
                .price(stockPriceDto.getPrice())
                .build();
    }

    public StockPriceDto mapToPriceDto(final StockPrice stockPrice) {
        return StockPriceDto.builder()
                .shortName(stockPrice.getShortName())
                .currency(stockPrice.getCurrency())
                .exchangeName(stockPrice.getExchangeName())
                .symbol(stockPrice.getSymbol())
                .price(stockPrice.getPrice())
                .build();
    }
}
