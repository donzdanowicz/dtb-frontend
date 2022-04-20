package com.dtb_front;

import com.dtb_front.service.EntryService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private EntryService entryService = EntryService.getInstance();
    private Span title = new Span("DARE TO BUDGET!");
    private Button userViewButton = new Button();
    private Button entryViewButton = new Button();
    private Button reportViewButton = new Button();
    private Button netWorthViewButton = new Button();
    private Button currencyConverterViewButton = new Button();
    private Button stockMarketViewButton = new Button();

    public MainView() {
        title.getElement().getStyle().set("font-size", "45px");
        title.getStyle().set("margin", "40px");
        userViewButton.setText("ADD USER");
        userViewButton.addClickListener(e -> UI.getCurrent().navigate(UserView.class));
        userViewButton.getStyle().set("margin", "20px");
        entryViewButton.setText("ENTRIES");
        entryViewButton.addClickListener(e -> UI.getCurrent().navigate(EntryView.class));
        entryViewButton.getStyle().set("margin", "20px");
        reportViewButton.setText("REPORTS");
        reportViewButton.addClickListener(e -> UI.getCurrent().navigate(ReportView.class));
        reportViewButton.getStyle().set("margin", "20px");
        netWorthViewButton.setText("NET WORTH");
        netWorthViewButton.addClickListener(e -> UI.getCurrent().navigate(NetWorthView.class));
        netWorthViewButton.getStyle().set("margin", "20px");
        currencyConverterViewButton.setText("CURRENCY CONVERTER");
        currencyConverterViewButton.addClickListener(e -> UI.getCurrent().navigate(CurrencyConverterView.class));
        currencyConverterViewButton.getStyle().set("margin", "20px");
        stockMarketViewButton.setText("STOCK MARKET");
        stockMarketViewButton.addClickListener(e -> UI.getCurrent().navigate(StockMarketView.class));
        stockMarketViewButton.getStyle().set("margin", "20px");
        VerticalLayout mainContent = new VerticalLayout(title, userViewButton, entryViewButton, reportViewButton, netWorthViewButton,
                currencyConverterViewButton, stockMarketViewButton);
        mainContent.setSizeFull();
        mainContent.setAlignItems(Alignment.CENTER);
        add(mainContent);
        setSizeFull();
    }
}
