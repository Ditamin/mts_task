package com.example.tariffParser.controller;

import com.example.tariffParser.model.Tariff;
import com.example.tariffParser.service.TariffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TariffController {
    private final TariffService tariffService;

    @GetMapping("/")
    List<Tariff> getTariff() {
        return tariffService.getTariffs();
    }
}
