package com.example.tariffParser.service;

import com.example.tariffParser.model.Tariff;
import com.example.tariffParser.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TariffService {
    private final PageParser parser;
    private final TariffRepository tariffRepository;

    public List<Tariff> getTariffs() {
        for (Tariff tariff : parser.getTariffs()) {
            tariffRepository.save(tariff);
        }

        return tariffRepository.findAll();
    }
}
