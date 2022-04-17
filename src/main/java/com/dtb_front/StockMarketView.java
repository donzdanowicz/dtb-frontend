package com.dtb_front;

import com.dtb_front.domain.StockPrice;
import com.dtb_front.service.StockMarketService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("stockMarket")
public class StockMarketView extends VerticalLayout {

    private StockMarketService stockMarketService = StockMarketService.getInstance();
    private Button mainViewButton = new Button();
    private Button entryViewButton = new Button();
    private Button reportViewButton = new Button();
    private Button netWorthViewButton = new Button();
    private Button currencyConverterViewButton = new Button();
    private Button stockMarketViewButton = new Button();
    private Button getStockPriceButton = new Button();
    private Span stockPrice = new Span("");
    private Span stockName = new Span("");
    private Span stockMarket = new Span("");
    private Span stockCurrency = new Span("");
    private TextField symbol = new TextField("Stock Symbol");
    private Div topBar = new Div(mainViewButton, entryViewButton, reportViewButton, netWorthViewButton,
            currencyConverterViewButton, stockMarketViewButton);
    private Div getStockPriceBar = new Div(symbol, getStockPriceButton);
    private Div stockPriceBar = new Div(stockPrice, stockCurrency);
    private Span space = new Span("");

    public StockMarketView() {
        mainViewButton.setText("MAIN VIEW");
        mainViewButton.addClickListener(e -> UI.getCurrent().navigate(MainView.class));
        entryViewButton.setText("Entries");
        entryViewButton.addClickListener(e -> UI.getCurrent().navigate(EntryView.class));
        reportViewButton.setText("Reports");
        reportViewButton.addClickListener(e -> UI.getCurrent().navigate(ReportView.class));
        netWorthViewButton.setText("Net Worth");
        netWorthViewButton.addClickListener(e -> UI.getCurrent().navigate(NetWorthView.class));
        currencyConverterViewButton.setText("Currency Converter");
        currencyConverterViewButton.addClickListener(e -> UI.getCurrent().navigate(CurrencyConverterView.class));
        stockMarketViewButton.setText("STOCK MARKET");
        mainViewButton.getStyle().set("margin", "5px");
        entryViewButton.getStyle().set("margin", "5px");
        reportViewButton.getStyle().set("margin", "5px");
        netWorthViewButton.getStyle().set("margin", "5px");
        currencyConverterViewButton.getStyle().set("margin", "5px");
        stockMarketViewButton.getStyle().set("margin", "5px");
        getStockPriceButton.getStyle().set("margin", "5px");
        getStockPriceButton.setText("Get Stock Price");
        getStockPriceButton.addClickListener(e -> getStockPrice(symbol.getValue()));
        stockName.getStyle().set("margin", "5px");
        stockMarket.getStyle().set("margin", "5px");
        stockPrice.getStyle().set("margin", "5px");
        stockCurrency.getStyle().set("margin", "5px");
        Anchor anchor = new Anchor("https://finance.yahoo.com/", "Full list of stock symbols on Yahoo Finances");
        anchor.setTarget("_blank");
        anchor.getStyle().set("margin", "5px");
        add(topBar, getStockPriceBar, stockName, stockMarket, stockPriceBar, space, anchor);
        setSizeFull();
    }

    public void getStockPrice(String symbol) {
        StockPrice selectedStockStockPrice =  stockMarketService.getStockMarketPrice(symbol);
        stockName.setText("Name: " + selectedStockStockPrice.getShortName());
        stockMarket.setText("Market: " + selectedStockStockPrice.getExchangeName());
        stockPrice.setText("Current Price: " + selectedStockStockPrice.getPrice());
        stockCurrency.setText(selectedStockStockPrice.getCurrency());
    }
}
