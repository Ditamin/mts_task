package com.example.tariffParser.service;

import com.example.tariffParser.model.Tariff;

import java.io.IOException;
import java.util.List;

public interface PageParser {
    List<Tariff> getTariffs() throws IOException;
}
