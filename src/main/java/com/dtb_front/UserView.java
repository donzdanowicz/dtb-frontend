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
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@Route("user")
public class UserView extends VerticalLayout {

    private UserService userService = UserService.getInstance();
    private UserForm form = new UserForm(this);
    private Grid<User> grid = new Grid<>(User.class);
    private Button newUserButton = new Button();
    private Button mainViewButton = new Button();
    private ComboBox<User> filter = new ComboBox<>();
    private Div topBar = new Div(mainViewButton);
    private Div dateBar = new Div(newUserButton, filter);

    public UserView() {
        newUserButton.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setUser(new User());
        });
        newUserButton.setText("Create new User");
        newUserButton.getStyle().set("margin", "5px");
        mainViewButton.setText("MAIN VIEW");
        mainViewButton.addClickListener(e -> UI.getCurrent().navigate(MainView.class));
        mainViewButton.getStyle().set("margin", "5px");
        grid.setColumns("firstName", "lastName", "created", "currency", "active");
        grid.getColumnByKey("firstName").setAutoWidth(true);
        grid.getColumnByKey("lastName").setAutoWidth(true);
        grid.getColumnByKey("created").setAutoWidth(true);
        grid.getColumnByKey("currency").setAutoWidth(true);
        grid.getColumnByKey("active").setAutoWidth(true);
        grid.setColumnReorderingAllowed(true);
        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setHeightFull();
        add(topBar, dateBar, mainContent);
        form.setUser(null);
        setSizeFull();
        refresh();
        grid.asSingleSelect().addValueChangeListener(event -> form.setUser(grid.asSingleSelect().getValue()));
    }

    public void refresh() {
        grid.setItems(userService.getUsers());
    }

}
