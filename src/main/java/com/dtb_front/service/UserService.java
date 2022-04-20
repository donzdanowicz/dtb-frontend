package com.dtb_front.service;

import com.dtb_front.client.NetWorthClient;
import com.dtb_front.client.UserClient;
import com.dtb_front.domain.NetWorth;
import com.dtb_front.domain.User;
import com.dtb_front.domain.UserDto;
import com.dtb_front.mapper.NetWorthMapper;
import com.dtb_front.mapper.UserMapper;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List users;
    private static UserService userService;
    private UserClient userClient = new UserClient(new RestTemplate());
    private UserMapper userMapper = new UserMapper();

    public UserService() {
        this.users = getUsers();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getUsers() {
        List<User> users = userMapper.mapToUserList(userClient.getUsers());
        return users;
    }

    public void addUser(User user) {
        userClient.addUser(userMapper.mapToUserDto(user));
    }

    public void updateUser(User user) {
        userClient.updateUser(userMapper.mapToUserDto(user));
    }

    public void save(User user) {
        addUser(user);
    }

    public void update(User user) {
        updateUser(user);
    }

    public void delete(User user) {
        userClient.deleteUser(userMapper.mapToUserDto(user));
    }

}
