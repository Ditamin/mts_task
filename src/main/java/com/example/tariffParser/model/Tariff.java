package com.example.tariffParser.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;
    String name;
    String description;
    String price;
    int minutesCount;
    int gbCount;
    String url;

    public Tariff(String name, String description, String price, int minutesCount, int gbCount, String url) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.minutesCount = minutesCount;
        this.gbCount = gbCount;
        this.url = url;
    }
}
