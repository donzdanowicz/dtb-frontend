package com.dtb_front.client;

import com.dtb_front.domain.EntryDto;
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
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/netWorth/date")
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

    public NetWorthDto addNetWorth(NetWorthDto netWorthDto) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/netWorth")
                .queryParam("realEstate", netWorthDto.getRealEstate())
                .queryParam("cash", netWorthDto.getCash())
                .queryParam("vehicles", netWorthDto.getVehicles())
                .queryParam("savingsAndInvestments", netWorthDto.getSavingsAndInvestments())
                .queryParam("foreignCurrencies", netWorthDto.getForeignCurrencies())
                .queryParam("stocks", netWorthDto.getStocks())
                .queryParam("collections", netWorthDto.getCollections())
                .queryParam("homeContent", netWorthDto.getHomeContent())
                .queryParam("otherAssets", netWorthDto.getOtherAssets())
                .queryParam("mortgage", netWorthDto.getMortgage())
                .queryParam("loans", netWorthDto.getLoans())
                .queryParam("creditCards", netWorthDto.getCreditCards())
                .queryParam("otherLiabilities", netWorthDto.getOtherLiabilities())
                .queryParam("date", netWorthDto.getDate())
                .queryParam("userId", netWorthDto.getUserId())
                .build()
                .encode()
                .toUri();

        return restTemplate.postForObject(url, netWorthDto, NetWorthDto.class);
    }

    public void updateNetWorth(NetWorthDto netWorthDto) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/netWorth")
                .queryParam("id", netWorthDto.getId())
                .queryParam("realEstate", netWorthDto.getRealEstate())
                .queryParam("cash", netWorthDto.getCash())
                .queryParam("vehicles", netWorthDto.getVehicles())
                .queryParam("savingsAndInvestments", netWorthDto.getSavingsAndInvestments())
                .queryParam("foreignCurrencies", netWorthDto.getForeignCurrencies())
                .queryParam("stocks", netWorthDto.getStocks())
                .queryParam("collections", netWorthDto.getCollections())
                .queryParam("homeContent", netWorthDto.getHomeContent())
                .queryParam("otherAssets", netWorthDto.getOtherAssets())
                .queryParam("mortgage", netWorthDto.getMortgage())
                .queryParam("loans", netWorthDto.getLoans())
                .queryParam("creditCards", netWorthDto.getCreditCards())
                .queryParam("otherLiabilities", netWorthDto.getOtherLiabilities())
                .queryParam("date", netWorthDto.getDate())
                .queryParam("userId", netWorthDto.getUserId())
                .build()
                .encode()
                .toUri();

        restTemplate.put(url, netWorthDto);
    }

    public void deleteNetWorth(NetWorthDto netWorthDto) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/netWorth/" + netWorthDto.getId())
                .build()
                .encode()
                .toUri();

        restTemplate.delete(url);
    }
}
