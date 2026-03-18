package com.unisul.here.controller;

import com.unisul.here.model.RegistroPresenca;
import com.unisul.here.service.RegistroPresencaService;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/presenca")
public class RegistroPresencaController {

    private final RegistroPresencaService service;

    public RegistroPresencaController(RegistroPresencaService service) {
        this.service = service;
    }

    @PostMapping("/registrar")
    public RegistroPresenca registrarPresenca() {
        return service.registrarPresenca();
    }
}