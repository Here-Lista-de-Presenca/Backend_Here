package com.unisul.here.service;

import com.unisul.here.model.CadastroUsuario;
import com.unisul.here.model.RegistroPresenca;
import com.unisul.here.repository.CadastroUsuarioRepository;
import com.unisul.here.repository.RegistroPresencaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.LocalDateTime;

@Service
public class RegistroPresencaService {

    private final RegistroPresencaRepository presencaRepository;
    private final CadastroUsuarioRepository usuarioRepository;

    public RegistroPresencaService(RegistroPresencaRepository presencaRepository,
                                   CadastroUsuarioRepository usuarioRepository) {
        this.presencaRepository = presencaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public RegistroPresenca registrarPresenca(Long usuarioId) {

        LocalTime agora = LocalTime.now();

        LocalTime inicioPermitido = LocalTime.of(19, 50);
        LocalTime limiteRegistro = LocalTime.of(21, 0);

        if (agora.isBefore(inicioPermitido)) {
            throw new RuntimeException("Registro ainda não liberado. Aguarde até 19:50.");
        }

        if (agora.isAfter(limiteRegistro)) {
            throw new RuntimeException("Tempo de registro encerrado.");
        }

        CadastroUsuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        RegistroPresenca registro = new RegistroPresenca();
        registro.setHoraRegistro(LocalDateTime.now());
        registro.setUsuario(usuario);

        return presencaRepository.save(registro);
    }
}