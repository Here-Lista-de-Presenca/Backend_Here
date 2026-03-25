package com.unisul.here.service;

import com.unisul.here.model.CadastroUsuario;
import com.unisul.here.repository.CadastroUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroUsuarioService {

    private final CadastroUsuarioRepository usuarioRepository;

    public CadastroUsuarioService(CadastroUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public CadastroUsuario salvar(CadastroUsuario user) {
        if (!user.getEmail().contains("@")) {
            throw new RuntimeException("Email inválido");
        }

        return usuarioRepository.save(user);
    }

    public CadastroUsuario login(String email, String senha) {
        CadastroUsuario user = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));

        if (!user.getPassword().equals(senha)) {
            throw new RuntimeException("Senha incorreta");
        }

        return user;
    }
}