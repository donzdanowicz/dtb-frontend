package com.dtb_front.service;

import com.dtb_front.client.CurrencyConverterClient;
import org.springframework.web.client.RestTemplate;

public class CurrencyConverterService {
    private static CurrencyConverterService currencyConverterService;
    private CurrencyConverterClient currencyConverterClient = new CurrencyConverterClient(new RestTemplate());

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
}
