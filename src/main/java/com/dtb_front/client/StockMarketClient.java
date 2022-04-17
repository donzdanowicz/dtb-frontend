package com.dtb_front.client;

import com.dtb_front.domain.StockPriceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class StockMarketClient {
    private final RestTemplate restTemplate;

    public StockPriceDto getStockMarketPrice(String symbol) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/api/stockMarket/price")
                .queryParam("symbol", symbol)
                .build()
                .encode()
                .toUri();

        StockPriceDto responseDto = restTemplate.getForObject(url, StockPriceDto.class);
        System.out.println(responseDto);
        return responseDto;
    }
}
