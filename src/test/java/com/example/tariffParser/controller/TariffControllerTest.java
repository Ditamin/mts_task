package com.example.tariffParser.controller;

import com.example.tariffParser.model.Tariff;
import com.example.tariffParser.service.TariffService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(TariffController.class)
public class TariffControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private TariffService tariffService;

    @Test
    void getTariffsTest() throws Exception {
        Tariff tariff = new Tariff("name", "all", "100 ₽", 2, 3, "url");
        List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(tariff);
        when(tariffService.getTariffs()).thenReturn(tariffs);

        mockMvc.perform(get("/tariffs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("name"))
                .andExpect(jsonPath("$[0].description").value("all"))
                .andExpect(jsonPath("$[0].price").value("100 ₽"))
                .andExpect(jsonPath("$[0].minutesCount").value(2))
                .andExpect(jsonPath("$[0].gbCount").value(3))
                .andExpect(jsonPath("$[0].url").value("url"));
    }

    @Test
    void getWrongPathTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isNotFound());
    }
}
