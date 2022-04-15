package com.dtb_front.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Entry {
    private Long id;
    private double income;
    private double food;
    private double housing;
    private double transportation;
    private double healthcare;
    private double personal;
    private double kids;
    private double entertainment;
    private double miscellaneous;
    private double travel;
    private double debts;
    private double savingAndInvesting;
    private EntryType type;
    private LocalDate date;
    private User user;
}
