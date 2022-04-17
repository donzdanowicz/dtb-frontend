package com.dtb_front.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class CurrencyConverterClient {
    private final RestTemplate restTemplate;

    public Double getConvertedCurrency(String from, String to, double amountFrom) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/api/currencyConverter")
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("amountFrom", amountFrom)
                .build()
                .encode()
                .toUri();

        Double response = restTemplate.getForObject(url, Double.class);

        return response;
    }
}
