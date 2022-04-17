package com.dtb_front;

import com.dtb_front.service.CurrencyConverterService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("currencyConverter")
public class CurrencyConverterView extends VerticalLayout {

    private CurrencyConverterService currencyConverterService = CurrencyConverterService.getInstance();
    private Button mainViewButton = new Button();
    private Button entryViewButton = new Button();
    private Button reportViewButton = new Button();
    private Button netWorthViewButton = new Button();
    private Button currencyConverterViewButton = new Button();
    private Button stockMarketViewButton = new Button();
    private Button convertCurrencyButton = new Button();
    private Span newAmount = new Span("");
    private TextField from = new TextField("From");
    private TextField to = new TextField("To");
    private NumberField oldAmount = new NumberField("Amount to Convert");
    private Div topBar = new Div(mainViewButton, entryViewButton, reportViewButton, netWorthViewButton,
            currencyConverterViewButton, stockMarketViewButton);
    private Div currencyConverterBar = new Div(from, to, oldAmount, convertCurrencyButton);
    private Div convertedAmountBar = new Div(newAmount);

    public CurrencyConverterView() {
        mainViewButton.setText("MAIN VIEW");
        mainViewButton.addClickListener(e -> UI.getCurrent().navigate(MainView.class));
        entryViewButton.setText("Entries");
        entryViewButton.addClickListener(e -> UI.getCurrent().navigate(EntryView.class));
        reportViewButton.setText("Reports");
        reportViewButton.addClickListener(e -> UI.getCurrent().navigate(ReportView.class));
        netWorthViewButton.setText("Net Worth");
        netWorthViewButton.addClickListener(e -> UI.getCurrent().navigate(NetWorthView.class));
        currencyConverterViewButton.setText("CURRENCY CONVERTER");
        stockMarketViewButton.setText("Stock Market");
        stockMarketViewButton.addClickListener(e -> UI.getCurrent().navigate(StockMarketView.class));
        stockMarketViewButton.getStyle().set("margin", "5px");
        mainViewButton.getStyle().set("margin", "5px");
        entryViewButton.getStyle().set("margin", "5px");
        reportViewButton.getStyle().set("margin", "5px");
        netWorthViewButton.getStyle().set("margin", "5px");
        currencyConverterViewButton.getStyle().set("margin", "5px");
        convertCurrencyButton.getStyle().set("margin", "5px");
        convertCurrencyButton.setText("CONVERT");
        convertCurrencyButton.addClickListener(e -> convert(from.getValue(), to.getValue(),
                oldAmount.getValue()));

        from.getStyle().set("margin", "5px");
        to.getStyle().set("margin", "5px");
        oldAmount.getStyle().set("margin", "5px");
        add(topBar, currencyConverterBar, convertedAmountBar);
        setSizeFull();
    }

    public Double convert(String from, String to, double amountFrom) {
        Double convertedCurrency =  currencyConverterService.getConvertedCurrency(from, to, amountFrom);
        System.out.println(convertedCurrency);
        newAmount.setText("Converted Amount: " + convertedCurrency);
        return convertedCurrency;
    }
}
