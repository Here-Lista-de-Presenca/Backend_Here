package com.unisul.here.Controllers;

import com.unisul.here.model.CadastroUsuario;
import com.unisul.here.repository.CadastroUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CadastroUsuarioControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CadastroUsuarioRepository cadastroUsuarioRepository;

    @BeforeEach
    public void setUp() {
        cadastroUsuarioRepository.deleteAll();
    }

    @Test
    @DisplayName("Cenário Sucesso: Cadastro de novo usuário")
    void deveCadastrarUsuarioComSucesso() {
        // ARRANGE
        java.util.Map<String, String> usuarioJson = new java.util.HashMap<>();
        usuarioJson.put("email", "estudante@unisul.br");
        usuarioJson.put("password", "senha123");

        // ACT
        ResponseEntity<CadastroUsuario> response = restTemplate.postForEntity("/users", usuarioJson, CadastroUsuario.class);

        // ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode(), "O cadastro deveria retornar 200 OK");
        assertNotNull(response.getBody());
        assertEquals("estudante@unisul.br", response.getBody().getEmail());
    }

    @Test
    @DisplayName("Cenário Erro: Cadastro com email inválido")
    void deveRetornarErroComEmailInvalido() {
        // ARRAMGE
        java.util.Map<String, String> usuarioJson = new java.util.HashMap<>();
        usuarioJson.put("email", "email sem formatacao");
        usuarioJson.put("password", "senha123");

        // ACT
        ResponseEntity<CadastroUsuario> response = restTemplate.postForEntity("/users", usuarioJson, CadastroUsuario.class);

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