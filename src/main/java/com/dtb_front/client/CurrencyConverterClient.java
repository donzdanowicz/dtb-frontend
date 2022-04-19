package com.dtb_front.client;

import com.dtb_front.domain.Currency;
import com.dtb_front.domain.CurrencyDto;
import com.dtb_front.domain.EntryDto;
import com.dtb_front.domain.StockPriceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;

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

        CurrencyDto currencyDto = CurrencyDto.builder()
                .fromCurrency(from)
                .toCurrency(to)
                .rate(response/amountFrom)
                .amount(amountFrom)
                .convertedAmount(response)
                .created(LocalDateTime.now())
                .build();

        createCurrencyEntry(currencyDto);

        return response;
    }

    public CurrencyDto createCurrencyEntry(CurrencyDto currencyDto) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/currencies")
                .queryParam("fromCurrency", currencyDto.getFromCurrency())
                .queryParam("toCurrency", currencyDto.getToCurrency())
                .queryParam("rate", currencyDto.getRate())
                .queryParam("amount", currencyDto.getAmount())
                .queryParam("convertedAmount", currencyDto.getConvertedAmount())
                .queryParam("created", currencyDto.getCreated())
                .build()
                .encode()
                .toUri();

        return restTemplate.postForObject(url, currencyDto, CurrencyDto.class);
    }

    public CurrencyDto getLatestCurrency(String from, String to) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/currencies/latest")
                .queryParam("from", from)
                .queryParam("to", to)
                .build()
                .encode()
                .toUri();

        CurrencyDto currencyDto = restTemplate.getForObject(url, CurrencyDto.class);

        return currencyDto;
    }

    public List<CurrencyDto> getLatest10() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/currencies/latest10")
                .build()
                .encode()
                .toUri();

        try {
            CurrencyDto[] currenciesResponse = restTemplate.getForObject(url, CurrencyDto[].class);

            List<CurrencyDto> currencies = new ArrayList<>(Optional.ofNullable(currenciesResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));
            System.out.println(currencies);
            return currencies;
        } catch (RestClientException e) {
            //LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }



}
