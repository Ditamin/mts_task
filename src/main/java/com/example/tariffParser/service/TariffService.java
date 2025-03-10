package com.example.tariffParser.service;

import com.example.tariffParser.model.Tariff;
import com.example.tariffParser.repository.TariffRepository;
import com.example.tariffParser.service.parser.PageParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TariffService {
    @Autowired
    private final PageParser parser;
    @Autowired
    private final TariffRepository tariffRepository;

    public List<Tariff> getTariffs() throws IOException {
        List<Tariff> tariffs = parser.getTariffs();

        log.debug("Сохранение полученных тарифов");
        tariffRepository.deleteAll();
        tariffRepository.saveAll(tariffs);
        return tariffs;
    }
}
