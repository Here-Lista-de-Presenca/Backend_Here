package com.unisul.here.repository;

import com.unisul.here.model.CadastroUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CadastroUsuarioRepository extends JpaRepository<CadastroUsuario, Long> {
    Optional<CadastroUsuario> findByEmail(String email);
}