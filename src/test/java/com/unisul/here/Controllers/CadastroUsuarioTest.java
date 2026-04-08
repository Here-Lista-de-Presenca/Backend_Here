package com.unisul.here.Controllers;

import com.unisul.here.controller.CadastroUsuarioController;
import com.unisul.here.model.CadastroUsuario;
import com.unisul.here.service.CadastroUsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CadastroUsuarioController.class)
public class CadastroUsuarioTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CadastroUsuarioService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCadastrarUsuarioComSucesso() throws Exception {
        CadastroUsuario usuario = new CadastroUsuario();
        usuario.setEmail("teste@unisul.com");
        usuario.setPassword("123456");

        CadastroUsuario usuarioSalvo = new CadastroUsuario();
        usuarioSalvo.setId(1L);
        usuarioSalvo.setEmail("teste@unisul.com");

        when(service.salvar(any(CadastroUsuario.class))).thenReturn(usuarioSalvo);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("teste@unisul.com"));
    }

    @Test
    void deveRetornarErroQuandoEmailForInvalido() throws Exception {
        CadastroUsuario usuarioInvalido = new CadastroUsuario();
        usuarioInvalido.setEmail("email_sem_arroba.com");
        usuarioInvalido.setPassword("123");

        // Simulando a RuntimeException que você criou no Service
        when(service.salvar(any(CadastroUsuario.class)))
                .thenThrow(new RuntimeException("Email inválido"));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioInvalido)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Email inválido")));
    }

    @Test
    void deveFazerLoginComSucesso() throws Exception {
        CadastroUsuario loginRequest = new CadastroUsuario();
        loginRequest.setEmail("admin@admin.com");
        loginRequest.setPassword("admin123");

        CadastroUsuario usuarioMock = new CadastroUsuario();
        usuarioMock.setId(10L);
        usuarioMock.setEmail("admin@admin.com");

        when(service.login(anyString(), anyString())).thenReturn(usuarioMock);

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L));
    }

    @Test
    void deveFalharLoginComSenhaIncorreta() throws Exception {
        CadastroUsuario loginErrado = new CadastroUsuario();
        loginErrado.setEmail("usuario@teste.com");
        loginErrado.setPassword("senha_errada");

        when(service.login(anyString(), anyString()))
                .thenThrow(new RuntimeException("Senha incorreta"));

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginErrado)))
                .andExpect(status().isBadRequest());
    }
}