package com.unisul.here.Controllers;

import com.unisul.here.controller.RegistroPresencaController;
import com.unisul.here.model.RegistroPresenca;
import com.unisul.here.service.RegistroPresencaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.http.MediaType;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(RegistroPresencaController.class)
public class RegistroPresencaTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RegistroPresencaService registroPresencaService;

    // Localização correta (R. Antônio Dib Mussi, 366)
    private final Map<String, Double> localizacaoCorreta = Map.of(
            "latitude", -27.5935,
            "longitude", -48.5528
    );

    @Test
    void DeveRegistrarPresencaComSucesso() throws Exception {
        RegistroPresenca mockPresenca = new RegistroPresenca();
        mockPresenca.setId(1L);
        mockPresenca.setHoraRegistro(LocalDateTime.now());

        when(registroPresencaService.registrarPresenca(eq(1L), any(LocalDateTime.class), anyDouble(), anyDouble()))
                .thenReturn(mockPresenca);

        mockMvc.perform(post("/presenca/registrar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(localizacaoCorreta)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void TentaRegistrarPresencaAs19e40() throws Exception {
        LocalDateTime hora1940 = LocalDateTime.of(2026, 4, 7, 19, 40);

        when(registroPresencaService.registrarPresenca(eq(1L), eq(hora1940), anyDouble(), anyDouble()))
                .thenThrow(new RuntimeException("Registro ainda não liberado. Aguarde até 19:50."));

        mockMvc.perform(post("/presenca/registrar/1")
                        .param("momento", hora1940.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(localizacaoCorreta)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("19:50")));
    }

    @Test
    void TentaRegistrarPresencaAs21e20() throws Exception {
        LocalDateTime hora2120 = LocalDateTime.of(2026, 4, 7, 21, 20);

        when(registroPresencaService.registrarPresenca(eq(1L), eq(hora2120), anyDouble(), anyDouble()))
                .thenThrow(new RuntimeException("Tempo de registro encerrado."));

        mockMvc.perform(post("/presenca/registrar/1")
                        .param("momento", hora2120.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(localizacaoCorreta)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("encerrado")));
    }

    @Test
    void TentaRegistrarPresencaEmLocalizacaoInvalida() throws Exception {
        Map<String, Double> localizacaoErrada = Map.of(
                "latitude", -28.0000,
                "longitude", -49.0000
        );

        when(registroPresencaService.registrarPresenca(eq(1L), any(LocalDateTime.class), anyDouble(), anyDouble()))
                .thenThrow(new RuntimeException("Usuário fora do local permitido para registro."));

        mockMvc.perform(post("/presenca/registrar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(localizacaoErrada)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("fora do local permitido")));
    }
}