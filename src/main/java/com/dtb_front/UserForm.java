package com.dtb_front;

import com.dtb_front.domain.Entry;
import com.dtb_front.domain.EntryType;
import com.dtb_front.domain.User;
import com.dtb_front.service.EntryService;
import com.dtb_front.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends FormLayout {
    private UserView userView;
    private UserService service = UserService.getInstance();
    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField currency = new TextField("Currency");
    private ComboBox<Boolean> active = new ComboBox("Active");
    private Button save = new Button("Save");
    private Button update = new Button("Update");
    private Button delete = new Button("Delete");
    private Binder<User> binder = new Binder<User>(User.class);

    public UserForm(UserView userView) {
        this.userView = userView;
        active.setItems(true, false);
        HorizontalLayout buttons = new HorizontalLayout(save, update, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(firstName, lastName, currency, active, buttons);
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
        update.addClickListener(event -> update());
        delete.addClickListener(event -> delete());
    }

    private void save() {
        User user = binder.getBean();
        service.save(user);
        userView.refresh();
        setUser(null);
    }

    private void update() {
        User user = binder.getBean();
        service.update(user);
        userView.refresh();
        setUser(null);
    }

    private void delete() {
        User user = binder.getBean();
        service.delete(user);
        userView.refresh();
        setUser(null);
    }

    public void setUser(User user) {
        binder.setBean(user);

        if (user == null) {
            setVisible(false);
        } else {
            setVisible(true);
            firstName.focus();
        }
    }
}
