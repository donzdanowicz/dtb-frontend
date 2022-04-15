package com.dtb_front.service;

import com.dtb_front.client.EntryClient;
import com.dtb_front.domain.Entry;
import com.dtb_front.mapper.EntryMapper;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

//@NoArgsConstructor
//@RequiredArgsConstructor
//@Component
//@Service
public class EntryService {
    private List entries;
    private static EntryService entryService;
    private EntryClient entryClient = new EntryClient(new RestTemplate());
    private EntryMapper entryMapper = new EntryMapper();

    private EntryService() {
        this.entries = exampleEntries();
    }

    public static EntryService getInstance() {
        if (entryService == null) {
            entryService = new EntryService();
        }
        return entryService;
    }

    public List getEntries() {
        return new ArrayList<>(entries);
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    private List exampleEntries() {
        List<Entry> entries = entryMapper.mapToEntryList(entryClient.getEntries());
        System.out.println(entries);

        return entries;
    }

    public List filterReportByDate(LocalDate begin, LocalDate end) {
        List<Entry> entriesByDate = entryMapper.mapToEntryList(entryClient.getReportByDate(begin, end));
        System.out.println(begin);
        System.out.println(end);
        return entriesByDate;
    }

}


