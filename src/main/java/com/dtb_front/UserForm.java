package com.dtb_front;

import com.dtb_front.domain.Entry;
import com.dtb_front.domain.EntryType;
import com.dtb_front.domain.User;
import com.dtb_front.service.EntryService;
import com.dtb_front.service.UserService;
import com.vaadin.flow.component.UI;
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
    private Button save = new Button("Save");
    private Binder<User> binder = new Binder<User>(User.class);

    public UserForm(UserView userView) {
        this.userView = userView;
        HorizontalLayout buttons = new HorizontalLayout(save);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(firstName, lastName, currency, buttons);
        binder.bindInstanceFields(this);
        save.addClickListener(event -> save());
    }

    private void save() {
        User user = binder.getBean();
        service.save(user);
        setUser(null);
        UI.getCurrent().navigate(MainView.class);
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
