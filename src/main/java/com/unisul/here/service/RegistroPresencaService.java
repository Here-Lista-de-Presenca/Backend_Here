package com.unisul.here.service;

import com.unisul.here.model.RegistroPresenca;
import com.unisul.here.repository.RegistroPresencaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.LocalDateTime;

@Service
public class RegistroPresencaService {

    private final RegistroPresencaRepository repository;

    public RegistroPresencaService(RegistroPresencaRepository repository) {
        this.repository = repository;
    }

    public RegistroPresenca registrarPresenca() {

        LocalTime agora = LocalTime.now();

        LocalTime inicioPermitido = LocalTime.of(19, 50);
        LocalTime limiteRegistro = LocalTime.of(21, 0);

        if (agora.isBefore(inicioPermitido)) {
            throw new RuntimeException("Registro ainda não liberado. Aguarde até 19:50.");
        }

        if (agora.isAfter(limiteRegistro)) {
            throw new RuntimeException("Tempo de registro encerrado.");
        }

        RegistroPresenca registro = new RegistroPresenca();
        registro.setHoraRegistro(LocalDateTime.now());

        return repository.save(registro);
    }
}