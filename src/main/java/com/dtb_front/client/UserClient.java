package com.dtb_front.client;

import com.dtb_front.domain.StockPriceDto;
import com.dtb_front.domain.User;
import com.dtb_front.domain.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class UserClient {
    private final RestTemplate restTemplate;


    public List<UserDto> getUsers() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/users")
                .build()
                .encode()
                .toUri();

        try {
            UserDto[] userResponse = restTemplate.getForObject(url, UserDto[].class);

            List<UserDto> users = new ArrayList<>(Optional.ofNullable(userResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));
            return users;
        } catch (RestClientException e) {
            //LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public UserDto addUser(UserDto userDto) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/users")
                .queryParam("firstName", userDto.getFirstName())
                .queryParam("lastName", userDto.getLastName())
                .queryParam("created", userDto.getCreated())
                .queryParam("currency", userDto.getCurrency())
                .queryParam("active", userDto.isActive())
                .build()
                .encode()
                .toUri();

        return restTemplate.postForObject(url, userDto, UserDto.class);
    }

    public void updateUser(UserDto userDto) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/users")
                .queryParam("id", userDto.getId())
                .queryParam("firstName", userDto.getFirstName())
                .queryParam("lastName", userDto.getLastName())
                .queryParam("created", userDto.getCreated())
                .queryParam("currency", userDto.getCurrency())
                .queryParam("active", userDto.isActive())
                .build()
                .encode()
                .toUri();

        restTemplate.put(url, userDto);
    }

    public void deleteUser(UserDto userDto) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/users/" + userDto.getId())
                .build()
                .encode()
                .toUri();

        restTemplate.delete(url);
    }
}
