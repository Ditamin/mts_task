package com.example.tariffParser.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
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
