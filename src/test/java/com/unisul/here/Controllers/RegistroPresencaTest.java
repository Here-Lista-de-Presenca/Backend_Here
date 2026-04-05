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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.http.MediaType;


@WebMvcTest(RegistroPresencaController.class)
public class RegistroPresencaTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RegistroPresencaService registroPresencaService;

    @Test
    void DeveRegistrarPresencaComSucesso() throws Exception {
        RegistroPresenca mockPresenca = new RegistroPresenca();
        mockPresenca.setId(1L);
        mockPresenca.setHoraRegistro(LocalDateTime.now());

        when(registroPresencaService.registrarPresenca(eq(1L), any(LocalDateTime.class)))
                .thenReturn(mockPresenca);

        mockMvc.perform(post("/presenca/registrar/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void TentaRegistrarPresencaAntesDaHora() throws Exception {
        LocalDateTime horaInvalida = LocalDateTime.of(2026, 4, 2, 19, 40);

        when(registroPresencaService.registrarPresenca(eq(1L), any(LocalDateTime.class)))
                .thenThrow(new RuntimeException("Registro ainda não liberado. Aguarde até 19:50."));

        mockMvc.perform(post("/presenca/registrar/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("19:50")));
    }

    @Test
    void TentaRegistrarPresencaAposAHora() throws Exception {
        when(registroPresencaService.registrarPresenca(eq(1L), any(LocalDateTime.class)))
                .thenThrow(new RuntimeException("Tempo de registro encerrado."));

        mockMvc.perform(post("/presenca/registrar/1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("encerrado")));
    }
}
