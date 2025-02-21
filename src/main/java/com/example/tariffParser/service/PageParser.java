package com.example.tariffParser.service;

import com.example.tariffParser.model.Tariff;

import java.util.List;

public interface PageParser {
    List<Tariff> getTariffs();
}
