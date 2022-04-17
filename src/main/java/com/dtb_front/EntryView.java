package com.dtb_front;

import com.dtb_front.domain.Entry;
import com.dtb_front.domain.EntryType;
import com.dtb_front.service.EntryService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.SortDirection;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.time.LocalDate;

@Route("entry")
public class EntryView extends VerticalLayout {

    private EntryService entryService = EntryService.getInstance();
    private EntryForm form = new EntryForm(this);
    private Grid<Entry> grid = new Grid<>(Entry.class);
    private DatePicker beginDate = new DatePicker("Range begin date");
    private DatePicker endDate = new DatePicker("Range end date");
    private ComboBox<EntryType> filter = new ComboBox<>();
    private Button filterButton = new Button();
    private Button newEntryButton = new Button();
    private Button submitDateButton = new Button();
    private Button mainViewButton = new Button();
    private Button entryViewButton = new Button();
    private Button reportViewButton = new Button();
    private Button netWorthViewButton = new Button();
    private Button currencyConverterViewButton = new Button();
    private Div topBar = new Div(mainViewButton, entryViewButton, reportViewButton, netWorthViewButton,
            currencyConverterViewButton);
    private Div dateBar = new Div(newEntryButton, filter, filterButton, beginDate, endDate, submitDateButton);

    public EntryView() {
        newEntryButton.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setEntry(new Entry());
        });
        newEntryButton.setText("Add new Entry");
        newEntryButton.getStyle().set("margin", "5px");
        filter.setItems(EntryType.values());
        filter.setPlaceholder("Filter by type");
        filter.getStyle().set("margin", "5px");
        filter.setClearButtonVisible(true);
        filterButton.setText("Filter");
        filterButton.addClickListener(e -> update(filter.getValue()));
        filterButton.getStyle().set("margin", "5px");
        beginDate.addValueChangeListener(e -> endDate.setMin(e.getValue()));
        beginDate.getStyle().set("margin", "5px");
        endDate.addValueChangeListener(e -> beginDate.setMax(e.getValue()));
        endDate.getStyle().set("margin", "5px");
        submitDateButton.setText("Submit");
        submitDateButton.addClickListener(e -> update(beginDate.getValue(), endDate.getValue()));
        submitDateButton.getStyle().set("margin", "5px");
        mainViewButton.setText("MAIN VIEW");
        mainViewButton.addClickListener(e -> UI.getCurrent().navigate(MainView.class));
        entryViewButton.setText("ENTRIES Refresh");
        entryViewButton.addClickListener(e -> refresh());
        reportViewButton.setText("Reports");
        reportViewButton.addClickListener(e -> UI.getCurrent().navigate(ReportView.class));
        netWorthViewButton.setText("Net Worth");
        netWorthViewButton.addClickListener(e -> UI.getCurrent().navigate(NetWorthView.class));
        mainViewButton.getStyle().set("margin", "5px");
        entryViewButton.getStyle().set("margin", "5px");
        reportViewButton.getStyle().set("margin", "5px");
        netWorthViewButton.getStyle().set("margin", "5px");
        currencyConverterViewButton.setText("Currency Converter");
        currencyConverterViewButton.addClickListener(e -> UI.getCurrent().navigate(CurrencyConverterView.class));
        currencyConverterViewButton.getStyle().set("margin", "5px");
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
        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setHeightFull();
        add(topBar, dateBar, mainContent);
        form.setEntry(null);
        setSizeFull();
        refresh();
        grid.asSingleSelect().addValueChangeListener(event -> form.setEntry(grid.asSingleSelect().getValue()));
    }

    public void refresh() {
        grid.setItems(entryService.getEntries());
    }

    private void update(LocalDate begin, LocalDate end) {
        grid.setItems(entryService.getEntriesByDate(begin, end));
    }

    private void update(EntryType type) {
        grid.setItems(entryService.getEntriesByType(type));
    }
}
