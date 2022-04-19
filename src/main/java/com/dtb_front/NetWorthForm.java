package com.dtb_front;

import com.dtb_front.domain.NetWorth;
import com.dtb_front.service.NetWorthService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class NetWorthForm extends FormLayout {
    private NetWorthView netWorthView;
    private NetWorthService service = NetWorthService.getInstance();
    private Label space = new Label();
    private Label assets = new Label();
    private NumberField realEstate = new NumberField("Real Estate");
    private NumberField cash = new NumberField("Cash");
    private NumberField vehicles = new NumberField("Vehicles");
    private NumberField savingsAndInvestments = new NumberField("Savings and Investments");
    private NumberField foreignCurrencies = new NumberField("Foreign Currencies");
    private NumberField stocks = new NumberField("Stocks");
    private NumberField collections = new NumberField("Collections");
    private NumberField homeContent = new NumberField("Home Content");
    private NumberField otherAssets = new NumberField("Other Assets");
    private Label liabilities = new Label();
    private NumberField mortgage = new NumberField("Mortgage");
    private NumberField loans = new NumberField("Loans");
    private NumberField creditCards = new NumberField("Credit Cards");
    private NumberField otherLiabilities = new NumberField("Other Liabilities");
    private DatePicker date = new DatePicker("Date");
    private Button save = new Button("Save");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    private Binder<NetWorth> binder = new Binder<NetWorth>(NetWorth.class);


    public NetWorthForm(NetWorthView netWorthView) {
        this.netWorthView = netWorthView;
        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        assets.setText("ASSETS");
        assets.getStyle().set("size", "30px");
        liabilities.setText("LIABILITIES");
        assets.getStyle().set("alignment", "right");
        add(date, space, assets, realEstate, cash, vehicles, savingsAndInvestments, foreignCurrencies, stocks, collections,
                homeContent, otherAssets, liabilities, mortgage, loans, creditCards, otherLiabilities, buttons);
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        update.addClickListener(event -> update());
        delete.addClickListener(event -> delete());
    }

    private void save() {
        NetWorth netWorth= binder.getBean();
        service.save(netWorth);
        netWorthView.refresh();
        setNetWorth(null);
    }

    private void update() {
        NetWorth netWorth= binder.getBean();
        service.update(netWorth);
        netWorthView.refresh();
        setNetWorth(null);
    }

    private void delete() {
        NetWorth netWorth = binder.getBean();
        service.delete(netWorth);
        netWorthView.refresh();
        setNetWorth(null);
    }

    public void setNetWorth(NetWorth netWorth) {
        binder.setBean(netWorth);

        if (netWorth == null) {
            setVisible(false);
        } else {
            setVisible(true);
            date.focus();
        }
    }
}
