package com.unisul.here.controller;

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
    public ResponseEntity<?> registrar(@PathVariable Long id) {
        RegistroPresenca registro = service.registrarPresenca(id, LocalDateTime.now());
        return ResponseEntity.ok(registro);
    }
}