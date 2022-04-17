package com.dtb_front;

import com.dtb_front.domain.Entry;
import com.dtb_front.domain.EntryType;
import com.dtb_front.service.EntryService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;

import java.util.Locale;

public class EntryForm extends FormLayout {
    private EntryView entryView;
    private EntryService service = EntryService.getInstance();
    private NumberField income = new NumberField("Income");
    private NumberField food = new NumberField("Food");
    private NumberField housing = new NumberField("Housing");
    private NumberField transportation = new NumberField("Transportation");
    private NumberField healthcare = new NumberField("Healthcare");
    private NumberField personal = new NumberField("Personal");
    private NumberField kids = new NumberField("Kids");
    private NumberField entertainment = new NumberField("Entertainment");
    private NumberField miscellaneous = new NumberField("Miscellaneous");
    private NumberField travel = new NumberField("Travel");
    private NumberField debts = new NumberField("Debts");
    private NumberField savingAndInvesting = new NumberField("Saving and Investing");
    private DatePicker date = new DatePicker("Date");
    private ComboBox<EntryType> type = new ComboBox<>("Entry type");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Binder<Entry> binder = new Binder<Entry>(Entry.class);

    public EntryForm(EntryView entryView) {
        this.entryView = entryView;
        type.setItems(EntryType.values());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(type, date, income, food, housing, transportation, healthcare, personal, kids,
                entertainment, miscellaneous, travel, debts, savingAndInvesting, buttons);
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    private void save() {
        Entry entry = binder.getBean();
        service.save(entry);
        entryView.refresh();
        setEntry(null);
    }

    private void delete() {
        Entry entry = binder.getBean();
        service.delete(entry);
        entryView.refresh();
        setEntry(null);
    }

    public void setEntry(Entry entry) {
        binder.setBean(entry);

        if (entry == null) {
            setVisible(false);
        } else {
            setVisible(true);
            type.focus();
        }
    }
}
