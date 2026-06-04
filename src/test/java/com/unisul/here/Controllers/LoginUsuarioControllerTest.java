package com.unisul.here.Controllers;

import com.unisul.here.model.CadastroUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public class LoginUsuarioControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Cenário Erro: Login com email não cadastrado")
    void deveRetornarErroQuandoEmailNaoCadastrado() {
        // ARRANGE
        java.util.Map<String, String> usuarioJson = new java.util.HashMap<>();
        usuarioJson.put("email", "naoexiste@unisul.br");
        usuarioJson.put("password", "senha123");

        // ACT
        ResponseEntity<String> response = restTemplate.postForEntity("/users/login", usuarioJson, String.class);

        // ASSERT
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Cenário Erro: Login com senha inválida")
    void deveRetornarErroQuandoSenhaInvalida() {
        // ARRANGE
        java.util.Map<String, String> usuarioJson = new java.util.HashMap<>();
        usuarioJson.put("email", "usuariosenha@unisul.br");
        usuarioJson.put("password", "senha123");

        // cria o usuário primeiro
        restTemplate.postForEntity("/users", usuarioJson, CadastroUsuario.class);

        // altera para senha errada
        usuarioJson.put("password", "senhaErrada");

        // ACT
        ResponseEntity<String> response = restTemplate.postForEntity("/users/login", usuarioJson, String.class);

        // ASSERT
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Cenário Erro: Login com email inválido")
    void deveRetornarErroQuandoEmailInvalidoNoLogin() {
        // ARRANGE
        java.util.Map<String, String> usuarioJson = new java.util.HashMap<>();
        usuarioJson.put("email", "email invalido");
        usuarioJson.put("password", "senha123");

        // ACT
        ResponseEntity<String> response = restTemplate.postForEntity("/users/login", usuarioJson, String.class);

        // ASSERT
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Cenário Sucesso: Login de usuário existente")
    void deveRealizarLoginComSucesso() {
        //ARRANGE
        java.util.Map<String, String> usuarioJson = new java.util.HashMap<>();
        usuarioJson.put("email", "testelogin@unisul.br");
        usuarioJson.put("password", "senha123");
        ResponseEntity<CadastroUsuario> responseCreateUser = restTemplate.postForEntity("/users", usuarioJson, CadastroUsuario.class);

        //ACT
        ResponseEntity<CadastroUsuario> response = restTemplate.postForEntity("/users/login", usuarioJson, CadastroUsuario.class);

        // ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("testelogin@unisul.br", response.getBody().getEmail());
        assertEquals(null, response.getBody().getPassword());
    }
}