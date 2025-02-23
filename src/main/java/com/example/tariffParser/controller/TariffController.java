package com.example.tariffParser.controller;

import com.example.tariffParser.model.Tariff;
import com.example.tariffParser.service.TariffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TariffController {
    @Autowired
    private final TariffService tariffService;

    @GetMapping(value = "tariffs")
    List<Tariff> getTariff() throws IOException {
        log.info("Запрос на получение тарифов");
        return tariffService.getTariffs();
    }
}
