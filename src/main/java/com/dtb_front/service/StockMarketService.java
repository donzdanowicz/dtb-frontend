package com.dtb_front.service;

import com.dtb_front.client.StockMarketClient;
import com.dtb_front.domain.StockPrice;
import com.dtb_front.mapper.PriceMapper;
import org.springframework.web.client.RestTemplate;

public class StockMarketService {
    private static StockMarketService stockMarketService;
    private StockMarketClient stockMarketClient = new StockMarketClient(new RestTemplate());
    private PriceMapper priceMapper = new PriceMapper();

    private StockMarketService() {}

    public static StockMarketService getInstance() {
        if (stockMarketService == null) {
            stockMarketService = new StockMarketService();
        }
        return stockMarketService;
    }

    public StockPrice getStockMarketPrice(String symbol) {
        return priceMapper.mapToPrice(stockMarketClient.getStockMarketPrice(symbol));
    }
}
