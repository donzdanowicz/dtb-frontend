package com.dtb_front.mapper;

import com.dtb_front.domain.Currency;
import com.dtb_front.domain.CurrencyDto;
import com.dtb_front.domain.User;
import com.dtb_front.domain.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(),
                userDto.getCreated(), userDto.getCurrency(), userDto.isActive());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(),
                user.getCreated(), user.getCurrency(), user.isActive());
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    public List<User> mapToUserList(final List<UserDto> usersDto) {
        return usersDto.stream()
                .map(this::mapToUser)
                .collect(Collectors.toList());
    }

}