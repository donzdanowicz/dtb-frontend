package com.dtb_front.client;

import com.dtb_front.domain.EntryDto;
import com.dtb_front.domain.EntryType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EntryClient {
    //private static final Logger LOGGER = LoggerFactory.getLogger(DtbClient.class);
    private final RestTemplate restTemplate;

    public List<EntryDto> getEntries() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/entries")
                .build()
                .encode()
                .toUri();

        try {
            EntryDto[] entriesResponse = restTemplate.getForObject(url, EntryDto[].class);

            List<EntryDto> entries = new ArrayList<>(Optional.ofNullable(entriesResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));
            System.out.println(entries);
            return entries;
        } catch (RestClientException e) {
            //LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<EntryDto> getReports() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/entries/report")
                .build()
                .encode()
                .toUri();

        try {
            EntryDto[] entriesResponse = restTemplate.getForObject(url, EntryDto[].class);

            List<EntryDto> entries = new ArrayList<>(Optional.ofNullable(entriesResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));
            System.out.println(entries);
            return entries;
        } catch (RestClientException e) {
            //LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<EntryDto> getEntriesByType(EntryType type) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/entries/type")
                .queryParam("type", type)
                .build()
                .encode()
                .toUri();

        try {
            EntryDto[] entriesResponse = restTemplate.getForObject(url, EntryDto[].class);

            List<EntryDto> entries = new ArrayList<>(Optional.ofNullable(entriesResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));
            System.out.println(entries);
            return entries;
        } catch (RestClientException e) {
            //LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<EntryDto> getEntriesByDate(LocalDate begin, LocalDate end) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/entries/date")
                .queryParam("begin", begin)
                .queryParam("end", end)
                .build()
                .encode()
                .toUri();

        try {
            EntryDto[] entriesResponse = restTemplate.getForObject(url, EntryDto[].class);

            List<EntryDto> entries = new ArrayList<>(Optional.ofNullable(entriesResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));
            System.out.println(entries);
            return entries;
        } catch (RestClientException e) {
            //LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<EntryDto> getReportsByDate(LocalDate begin, LocalDate end) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/entries/report/date")
                .queryParam("begin", begin)
                .queryParam("end", end)
                .build()
                .encode()
                .toUri();

        try {
            EntryDto[] entriesResponse = restTemplate.getForObject(url, EntryDto[].class);

            List<EntryDto> entries = new ArrayList<>(Optional.ofNullable(entriesResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList()));
            System.out.println(entries);
            return entries;
        } catch (RestClientException e) {
            //LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public EntryDto addEntry(EntryDto entryDto) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/entries")
                .queryParam("income", entryDto.getIncome())
                .queryParam("food", entryDto.getFood())
                .queryParam("housing", entryDto.getHousing())
                .queryParam("transportation", entryDto.getTransportation())
                .queryParam("healthcare", entryDto.getHealthcare())
                .queryParam("personal", entryDto.getPersonal())
                .queryParam("kids", entryDto.getKids())
                .queryParam("entertainment", entryDto.getEntertainment())
                .queryParam("miscellaneous", entryDto.getMiscellaneous())
                .queryParam("travel", entryDto.getTravel())
                .queryParam("debts", entryDto.getDebts())
                .queryParam("savingAndInvesting", entryDto.getSavingAndInvesting())
                .queryParam("type", entryDto.getType())
                .queryParam("date", entryDto.getDate())
                .queryParam("userId", entryDto.getUserId())
                .build()
                .encode()
                .toUri();

        return restTemplate.postForObject(url, entryDto, EntryDto.class);
    }

    public void deleteEntry(EntryDto entryDto) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/entries/" + entryDto.getId())
                .build()
                .encode()
                .toUri();

        restTemplate.delete(url);
    }
}
