package com.dtb_front;

import com.dtb_front.domain.Entry;
import com.dtb_front.domain.EntryType;
import com.dtb_front.domain.User;
import com.dtb_front.domain.UserDto;
import com.dtb_front.service.EntryService;
import com.dtb_front.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@Route("")
public class UserView extends VerticalLayout {

    private UserService userService = UserService.getInstance();
    private UserForm form = new UserForm(this);
    private Button newUserButton = new Button();
    private Span title = new Span("DARE TO BUDGET!");
    //private Button mainViewButton = new Button();
    //private Div topBar = new Div(mainViewButton);
    //private Div dateBar = new Div(newUserButton);

    public UserView() {
        title.getElement().getStyle().set("font-size", "45px");
        title.getStyle().set("margin", "40px");
        if(userService.getUsers().isEmpty()) {
            newUserButton.addClickListener(e -> {
                form.setUser(new User());
            });
            newUserButton.setText("Create new User");
            newUserButton.getStyle().set("margin", "5px");
        } else {
        newUserButton.setText("RUN APPLICATION");
        newUserButton.addClickListener(e -> UI.getCurrent().navigate(MainView.class));
        newUserButton.getStyle().set("margin", "5px");
        }
        VerticalLayout buttons = new VerticalLayout(title, newUserButton);
        buttons.setSizeFull();
        buttons.setAlignItems(Alignment.CENTER);
        VerticalLayout mainContent = new VerticalLayout(form);
        mainContent.setSizeFull();
        mainContent.setAlignItems(Alignment.CENTER);
        add(buttons, mainContent);
        form.setUser(null);
        setSizeFull();
    }

}
