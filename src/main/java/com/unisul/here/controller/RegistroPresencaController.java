package com.unisul.here.controller;

import com.unisul.here.dto.LocalizacaoDTO;
import com.unisul.here.model.RegistroPresenca;
import com.unisul.here.service.RegistroPresencaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/presenca")
public class RegistroPresencaController {

    private final RegistroPresencaService service;

    public RegistroPresencaController(RegistroPresencaService service) {
        this.service = service;
    }

    @PostMapping("/registrar/{id}")
    public ResponseEntity<?> registrar(
            @PathVariable Long id,
            @RequestBody LocalizacaoDTO loc,
            @RequestParam(required = false) LocalDateTime momento) {

        LocalDateTime dataParaRegistro = (momento != null) ? momento : LocalDateTime.now();

        RegistroPresenca registro = service.registrarPresenca(id, dataParaRegistro, loc.latUser(), loc.lonUser());
        return ResponseEntity.ok(registro);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }
}

