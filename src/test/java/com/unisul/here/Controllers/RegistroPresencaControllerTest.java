package com.unisul.here.Controllers;

import com.unisul.here.dto.LocalizacaoDTO;
import com.unisul.here.model.CadastroUsuario;
import com.unisul.here.model.RegistroPresenca;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public class RegistroPresencaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final double LAT_VALIDA = -27.5935;
    private final double LON_VALIDA = -48.5528;

    @Test
    @DisplayName("Cenário Sucesso: Registrar presença dentro do horário e local permitidos")
    void deveRegistrarPresencaComSucesso() {
        // ARRANGE
        Map<String, String> usuarioJson = new HashMap<>();
        usuarioJson.put("email", "aluno@unisul.br");
        usuarioJson.put("password", "123456");
        ResponseEntity<CadastroUsuario> userResponse = restTemplate.postForEntity("/users", usuarioJson, CadastroUsuario.class);
        Long userId = userResponse.getBody().getId();

        LocalizacaoDTO loc = new LocalizacaoDTO(LAT_VALIDA, LON_VALIDA);
        String url = "/presenca/registrar/" + userId + "?momento=2026-04-29T20:30:00";

        // ACT
        ResponseEntity<RegistroPresenca> response = restTemplate.postForEntity(url, loc, RegistroPresenca.class);

        // ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(userId, response.getBody().getUsuario().getId());
    }

    @Test
    @DisplayName("Cenário Erro: Registrar presença antes do horário permitido")
    void deveRetornarErroHorarioAntecipado() {
        // ARRANGE
        LocalizacaoDTO loc = new LocalizacaoDTO(LAT_VALIDA, LON_VALIDA);
        String url = "/presenca/registrar/1?momento=2026-04-29T19:00:00";

        // ACT
        ResponseEntity<String> response = restTemplate.postForEntity(url, loc, String.class);

        // ASSERT
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Registro ainda não liberado. Aguarde até 19:50."));
    }

    @Test
    @DisplayName("Cenário Erro: Registrar presença depois do horário permitido")
    void deveRetornarErroHorarioRegistroPassou() {
        // ARRANGE
        LocalizacaoDTO loc = new LocalizacaoDTO(LAT_VALIDA, LON_VALIDA);
        String url = "/presenca/registrar/1?momento=2026-04-29T22:00:00";

        // ACT
        ResponseEntity<String> response = restTemplate.postForEntity(url, loc, String.class);

        // ASSERT
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Tempo de registro encerrado."));
    }

    @Test
    @DisplayName("Cenário Erro: Registrar presença fora do raio de 100 metros")
    void deveRetornarErroLocalizacaoDistante() {
        // ARRANGE
        LocalizacaoDTO loc = new LocalizacaoDTO(-23.5505, -46.6333);
        String url = "/presenca/registrar/1?momento=2026-04-29T20:00:00";

        // ACT
        ResponseEntity<String> response = restTemplate.postForEntity(url, loc, String.class);

        // ASSERT
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().contains("Usuário fora do local permitido para registro."));
    }

    @Test
    @DisplayName("Cenário Sucesso: Listar presenças por usuário")
    void deveListarPresencasPorUsuario() {
        // ACT
        ResponseEntity<RegistroPresenca[]> response = restTemplate.getForEntity("/presenca/usuario/1", RegistroPresenca[].class);

        // ASSERT
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}