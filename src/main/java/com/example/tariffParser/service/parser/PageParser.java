package com.example.tariffParser.service.parser;

import com.example.tariffParser.model.Tariff;

import java.io.IOException;
import java.util.List;

public interface PageParser {
    List<Tariff> getTariffs() throws IOException;
}
