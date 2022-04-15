package com.dtb_front.client;

import com.dtb_front.domain.NetWorthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.*;

@Component
@RequiredArgsConstructor
public class NetWorthClient {
    private final RestTemplate restTemplate;

    public List<NetWorthDto> getNetWorth() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/netWorth")
                .build()
                .encode()
                .toUri();

        try {
            NetWorthDto[] netWorthResponse = restTemplate.getForObject(url, NetWorthDto[].class);

            List<NetWorthDto> netWorthList = new ArrayList<>(Optional.ofNullable(netWorthResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));
            System.out.println(netWorthList);
            return netWorthList;
        } catch (RestClientException e) {
            //LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<NetWorthDto> getNetWorthByDate(LocalDate begin, LocalDate end) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/netWorth")
                .queryParam("begin", begin)
                .queryParam("end", end)
                .build()
                .encode()
                .toUri();

        try {
            NetWorthDto[] netWorthResponse = restTemplate.getForObject(url, NetWorthDto[].class);

            List<NetWorthDto> netWorthDtoList = new ArrayList<>(Optional.ofNullable(netWorthResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));
            System.out.println(netWorthDtoList);
            return netWorthDtoList;
        } catch (RestClientException e) {
            //LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
