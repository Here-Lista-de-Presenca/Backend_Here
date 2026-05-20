package com.unisul.here.controller;

import com.unisul.here.model.CadastroUsuario;
import com.unisul.here.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class CadastroUsuarioController {

    @Autowired
    private CadastroUsuarioService service;

    @PostMapping
    public ResponseEntity<CadastroUsuario> create(@Validated @RequestBody CadastroUsuario user) {
        CadastroUsuario savedUser = service.salvar(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<CadastroUsuario> login(@RequestBody CadastroUsuario usuario) {
        CadastroUsuario usuarioLogado = service.login(usuario.getEmail(), usuario.getPassword());
        return ResponseEntity.ok(usuarioLogado);
    }
}