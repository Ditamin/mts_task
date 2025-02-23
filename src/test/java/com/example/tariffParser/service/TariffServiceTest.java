package com.example.tariffParser.service;

import com.example.tariffParser.model.Tariff;
import com.example.tariffParser.repository.TariffRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TariffServiceTest {
    @Mock
    private PageParser parser;
    @Mock
    private TariffRepository repository;
    @InjectMocks
    private TariffService service;

    @Test
    void getTariffsTest() throws IOException {
        Tariff tariff = new Tariff("name", "all", "100 ₽", 2, 3, "url");
        List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(tariff);
        when(parser.getTariffs()).thenReturn(tariffs);

        var res = service.getTariffs();

        Assertions.assertEquals(res, tariffs);
    }

    @Test
    void getTariffsEmptyListTest() throws IOException {
        List<Tariff> tariffs = new ArrayList<>();
        when(parser.getTariffs()).thenReturn(tariffs);

        var res = service.getTariffs();

        Assertions.assertEquals(res, tariffs);
    }
}
