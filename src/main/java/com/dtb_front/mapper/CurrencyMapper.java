package com.dtb_front.mapper;

import com.dtb_front.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CurrencyMapper {
    public Currency mapToCurrency(final CurrencyDto currencyDto) {
        return Currency.builder()
                .id(currencyDto.getId())
                .fromCurrency(currencyDto.getFromCurrency())
                .toCurrency(currencyDto.getToCurrency())
                .rate(currencyDto.getRate())
                .amount(currencyDto.getAmount())
                .convertedAmount(currencyDto.getConvertedAmount())
                .created(currencyDto.getCreated())
                .build();
    }

    public CurrencyDto mapToCurrencyDto(final Currency currency) {
        return CurrencyDto.builder()
                .id(currency.getId())
                .fromCurrency(currency.getFromCurrency())
                .toCurrency(currency.getToCurrency())
                .rate(currency.getRate())
                .amount(currency.getAmount())
                .convertedAmount(currency.getConvertedAmount())
                .created(currency.getCreated())
                .build();
    }

    public List<Currency> mapToCurrencyList(final List<CurrencyDto> currenciesDto) {
        return currenciesDto.stream()
                .map(this::mapToCurrency)
                .collect(Collectors.toList());
    }
}

