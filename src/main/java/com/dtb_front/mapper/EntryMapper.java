package com.dtb_front.mapper;

import com.dtb_front.domain.Entry;
import com.dtb_front.domain.EntryDto;
import com.dtb_front.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EntryMapper {

    public Entry mapToEntry(final EntryDto entryDto) {
        return Entry.builder()
                .id(entryDto.getId())
                .income(entryDto.getIncome())
                .food(entryDto.getFood())
                .housing(entryDto.getHousing())
                .transportation(entryDto.getTransportation())
                .healthcare(entryDto.getHealthcare())
                .personal(entryDto.getPersonal())
                .kids(entryDto.getKids())
                .entertainment(entryDto.getEntertainment())
                .miscellaneous(entryDto.getMiscellaneous())
                .travel(entryDto.getTravel())
                .debts(entryDto.getDebts())
                .savingAndInvesting(entryDto.getSavingAndInvesting())
                .type(entryDto.getType())
                .date(entryDto.getDate())
                .user(new User(1L, "John", "Shoggoth", LocalDateTime.now(), "PLN"))
                //.user(userRepository.findById(entryDto.getUserId()).orElseThrow(UserNotFoundException::new))
                .build();
    }

    public EntryDto mapToEntryDto(final Entry entry) {
        return EntryDto.builder()
                .id(entry.getId())
                .income(entry.getIncome())
                .food(entry.getFood())
                .housing(entry.getHousing())
                .transportation(entry.getTransportation())
                .healthcare(entry.getHealthcare())
                .personal(entry.getPersonal())
                .kids(entry.getKids())
                .entertainment(entry.getEntertainment())
                .miscellaneous(entry.getMiscellaneous())
                .travel(entry.getTravel())
                .debts(entry.getDebts())
                .savingAndInvesting(entry.getSavingAndInvesting())
                .type(entry.getType())
                .date(entry.getDate())
                .userId(entry.getUser().getId())
                .build();
    }

    public List<Entry> mapToEntryList(final List<EntryDto> entriesDto) {
        return entriesDto.stream()
                .map(this::mapToEntry)
                .collect(Collectors.toList());
    }

    public List<EntryDto> mapToEntryDtoList(final List<Entry> entries) {
        return entries.stream()
                .map(this::mapToEntryDto)
                .collect(Collectors.toList());
    }
}
