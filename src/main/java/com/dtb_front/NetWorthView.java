package com.dtb_front;

import com.dtb_front.domain.Entry;
import com.dtb_front.domain.NetWorth;
import com.dtb_front.service.NetWorthService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import java.awt.*;
import java.time.LocalDate;

@Route(value = "networth")
public class NetWorthView extends VerticalLayout {
    private NetWorthService netWorthService = NetWorthService.getInstance();
    private NetWorthForm form = new NetWorthForm(this);
    private Grid<NetWorth> grid = new Grid<>(NetWorth.class);
    private DatePicker beginDate = new DatePicker("Range begin date");
    private DatePicker endDate = new DatePicker("Range end date");
    private Button newNetWorthButton = new Button();
    private Button submitDateButton = new Button();
    private Button mainViewButton = new Button();
    private Button entryViewButton = new Button();
    private Button reportViewButton = new Button();
    private Button netWorthViewButton = new Button();
    private Button currencyConverterViewButton = new Button();
    private Div topBar = new Div(mainViewButton, entryViewButton, reportViewButton, netWorthViewButton,
            currencyConverterViewButton);
    private Div dateBar = new Div(newNetWorthButton, beginDate, endDate, submitDateButton);

    public NetWorthView() {
        newNetWorthButton.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setNetWorth(new NetWorth());
        });
        newNetWorthButton.setText("Add new Net Worth Entry");
        newNetWorthButton.getStyle().set("margin", "5px");
        beginDate.addValueChangeListener(e -> endDate.setMin(e.getValue()));
        beginDate.getStyle().set("margin", "5px");
        endDate.addValueChangeListener(e -> beginDate.setMax(e.getValue()));
        endDate.getStyle().set("margin", "5px");
        submitDateButton.setText("Submit");
        submitDateButton.addClickListener(e -> update(beginDate.getValue(), endDate.getValue()));
        submitDateButton.getStyle().set("margin", "5px");
        mainViewButton.setText("MAIN VIEW");
        mainViewButton.addClickListener(e -> UI.getCurrent().navigate(MainView.class));
        entryViewButton.setText("Entries");
        entryViewButton.addClickListener(e -> UI.getCurrent().navigate(EntryView.class));
        reportViewButton.setText("Reports");
        reportViewButton.addClickListener(e -> UI.getCurrent().navigate(ReportView.class));
        netWorthViewButton.setText("NET WORTH Refresh");
        netWorthViewButton.addClickListener(e -> refresh());
        currencyConverterViewButton.setText("Currency Converter");
        currencyConverterViewButton.addClickListener(e -> UI.getCurrent().navigate(CurrencyConverterView.class));
        currencyConverterViewButton.getStyle().set("margin", "5px");
        mainViewButton.getStyle().set("margin", "5px");
        entryViewButton.getStyle().set("margin", "5px");
        reportViewButton.getStyle().set("margin", "5px");
        netWorthViewButton.getStyle().set("margin", "5px");

        grid.setColumns("date", "totalAssets", "totalLiabilities", "totalNetWorth", "realEstate", "cash", "vehicles",
                "savingsAndInvestments", "foreignCurrencies", "stocks", "collections", "homeContent", "otherAssets",
                "mortgage", "loans", "creditCards", "otherLiabilities");
        //"userId");
        grid.getColumnByKey("date").setAutoWidth(true);
        grid.getColumnByKey("totalAssets").setAutoWidth(true);
        grid.getColumnByKey("totalLiabilities").setAutoWidth(true);
        grid.getColumnByKey("totalNetWorth").setAutoWidth(true);
        grid.getColumnByKey("realEstate").setAutoWidth(true);
        grid.getColumnByKey("cash").setAutoWidth(true);
        grid.getColumnByKey("vehicles").setAutoWidth(true);
        grid.getColumnByKey("savingsAndInvestments").setAutoWidth(true);
        grid.getColumnByKey("foreignCurrencies").setAutoWidth(true);
        grid.getColumnByKey("stocks").setAutoWidth(true);
        grid.getColumnByKey("collections").setAutoWidth(true);
        grid.getColumnByKey("homeContent").setAutoWidth(true);
        grid.getColumnByKey("otherAssets").setAutoWidth(true);
        grid.getColumnByKey("mortgage").setAutoWidth(true);
        grid.getColumnByKey("loans").setAutoWidth(true);
        grid.getColumnByKey("creditCards").setAutoWidth(true);
        grid.getColumnByKey("otherLiabilities").setAutoWidth(true);
        grid.setColumnReorderingAllowed(true);
        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setHeightFull();
        add(topBar, dateBar, mainContent);
        form.setNetWorth(null);
        setSizeFull();
        refresh();
        grid.asSingleSelect().addValueChangeListener(event -> form.setNetWorth(grid.asSingleSelect().getValue()));
    }

    public void refresh() {
        grid.setItems(netWorthService.getNetWorthList());
    }

    private void update(LocalDate begin, LocalDate end) {
        grid.setItems(netWorthService.getNetWorthByDate(begin, end));
    }
}

