package com.dtb_front;

import com.dtb_front.domain.Entry;
import com.dtb_front.service.EntryService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;

import java.awt.*;
import java.time.LocalDate;

@Route(value = "report")
public class ReportView extends VerticalLayout {
    private EntryService entryService = EntryService.getInstance();
    private Grid<Entry> grid = new Grid<>(Entry.class);
    private DatePicker beginDate = new DatePicker("Range begin date");
    private DatePicker endDate = new DatePicker("Range end date");
    private Button submitDateButton = new Button();
    private Button mainViewButton = new Button();
    private Button reportViewButton = new Button();
    private Button netWorthViewButton = new Button();
    private Div topBar = new Div(mainViewButton, reportViewButton, netWorthViewButton);
    private Div dateBar = new Div(beginDate, endDate, submitDateButton);

    public ReportView() {
        beginDate.addValueChangeListener(e -> endDate.setMin(e.getValue()));
        beginDate.getStyle().set("margin", "5px");
        endDate.addValueChangeListener(e -> beginDate.setMax(e.getValue()));
        endDate.getStyle().set("margin", "5px");
        submitDateButton.setText("Submit");
        submitDateButton.addClickListener(e -> update(beginDate.getValue(), endDate.getValue()));
        submitDateButton.getStyle().set("margin", "5px");
        mainViewButton.setText("Main");
        mainViewButton.addClickListener(e -> UI.getCurrent().navigate(MainView.class));
        reportViewButton.setText("Refresh Reports");
        reportViewButton.addClickListener(e -> refresh());
        netWorthViewButton.setText("Net Worth");
        netWorthViewButton.addClickListener(e -> UI.getCurrent().navigate(NetWorthView.class));
        mainViewButton.getStyle().set("margin", "5px");
        reportViewButton.getStyle().set("margin", "5px");
        netWorthViewButton.getStyle().set("margin", "5px");
        grid.setColumns("date", "type", "income", "food", "housing", "transportation", "healthcare",
                "personal", "kids", "entertainment", "miscellaneous", "travel", "debts", "savingAndInvesting");
        //"userId");
        grid.getColumnByKey("date").setAutoWidth(true);
        grid.getColumnByKey("type").setAutoWidth(true);
        grid.getColumnByKey("income").setAutoWidth(true);
        grid.getColumnByKey("food").setAutoWidth(true);
        grid.getColumnByKey("housing").setAutoWidth(true);
        grid.getColumnByKey("transportation").setAutoWidth(true);
        grid.getColumnByKey("healthcare").setAutoWidth(true);
        grid.getColumnByKey("personal").setAutoWidth(true);
        grid.getColumnByKey("kids").setAutoWidth(true);
        grid.getColumnByKey("entertainment").setAutoWidth(true);
        grid.getColumnByKey("miscellaneous").setAutoWidth(true);
        grid.getColumnByKey("travel").setAutoWidth(true);
        grid.getColumnByKey("debts").setAutoWidth(true);
        grid.getColumnByKey("savingAndInvesting").setAutoWidth(true);
        grid.setColumnReorderingAllowed(true);
        add(topBar, dateBar, grid);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(entryService.getEntries());
    }

    private void update(LocalDate begin, LocalDate end) {
        grid.setItems(entryService.filterReportByDate(begin, end));
    }
}

