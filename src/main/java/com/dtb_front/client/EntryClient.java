package com.dtb_front.client;

import com.dtb_front.domain.EntryDto;
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

    public List<EntryDto> getReportByDate(LocalDate begin, LocalDate end) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/entries/reportByDate")
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
}
