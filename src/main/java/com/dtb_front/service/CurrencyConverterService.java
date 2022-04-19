package com.dtb_front.service;

import com.dtb_front.client.CurrencyConverterClient;
import com.dtb_front.domain.Currency;
import com.dtb_front.domain.CurrencyDto;
import com.dtb_front.mapper.CurrencyMapper;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class CurrencyConverterService {
    private static CurrencyConverterService currencyConverterService;
    private CurrencyConverterClient currencyConverterClient = new CurrencyConverterClient(new RestTemplate());
    private CurrencyMapper currencyMapper = new CurrencyMapper();

    private CurrencyConverterService() {}

    public static CurrencyConverterService getInstance() {
        if (currencyConverterService == null) {
            currencyConverterService = new CurrencyConverterService();
        }
        return currencyConverterService;
    }

    public Double getConvertedCurrency(String from, String to, double amountFrom) {
        return currencyConverterClient.getConvertedCurrency(from, to, amountFrom);
    }

    public CurrencyDto getLatestCurrency(String from, String to) {
        return currencyConverterClient.getLatestCurrency(from, to);
    }

    public List<CurrencyDto> getLatest10Conversions() {
        List<CurrencyDto> latest10 = currencyConverterClient.getLatest10();

        return latest10;
    }
}
