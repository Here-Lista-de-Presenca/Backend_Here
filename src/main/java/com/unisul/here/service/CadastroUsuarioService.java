package com.unisul.here.service;

import com.unisul.here.model.CadastroUsuario;
import com.unisul.here.repository.CadastroUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuarioService {

    private final CadastroUsuarioRepository repository2;

    public CadastroUsuarioService(CadastroUsuarioRepository repository) {
        this.repository2 = repository;
    }

    public CadastroUsuario salvar(CadastroUsuario user) {
        if (!user.getEmail().contains("@")) {
            throw new RuntimeException("Email inválido");
        }

        return repository2.save(user);
    }
}