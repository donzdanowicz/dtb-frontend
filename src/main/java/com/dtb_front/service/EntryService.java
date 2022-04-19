package com.dtb_front.service;

import com.dtb_front.client.EntryClient;
import com.dtb_front.domain.Entry;
import com.dtb_front.domain.EntryType;
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
        this.entries = getEntries();
    }

    public static EntryService getInstance() {
        if (entryService == null) {
            entryService = new EntryService();
        }
        return entryService;
    }

    public List getEntries() {
        //return new ArrayList<>(entries);
        List<Entry> entries = entryMapper.mapToEntryList(entryClient.getEntries());
        return entries;
    }

    public void addEntry(Entry entry) {
        entryClient.addEntry(entryMapper.mapToEntryDto(entry));
    }

    public void updateEntry(Entry entry) {
        entryClient.updateEntry(entryMapper.mapToEntryDto(entry));
    }

    public List getEntriesByType(EntryType type) {
        List<Entry> entriesByType = entryMapper.mapToEntryList(entryClient.getEntriesByType(type));
        return entriesByType;
    }

    public List getEntriesByDate(LocalDate begin, LocalDate end) {
        List<Entry> entriesByDate = entryMapper.mapToEntryList(entryClient.getEntriesByDate(begin, end));
        return entriesByDate;
    }

    public List getReports() {
        List<Entry> reports = entryMapper.mapToEntryList(entryClient.getReports());
        return reports;
    }

    public List getReportsByDate(LocalDate begin, LocalDate end) {
        List<Entry> entriesByDate = entryMapper.mapToEntryList(entryClient.getReportsByDate(begin, end));
        return entriesByDate;
    }

    public void save(Entry entry) {
        addEntry(entry);
    }

    public void update(Entry entry) {
        updateEntry(entry);
    }

    public void delete(Entry entry) {
        entryClient.deleteEntry(entryMapper.mapToEntryDto(entry));
    }
}


