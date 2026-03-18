package com.unisul.here.service;

import com.unisul.here.dao.AlunoDAO;
import com.unisul.here.dao.RegistroPresencaDAO;
import com.unisul.here.model.Aluno;
import com.unisul.here.model.RegistroPresenca;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistroPresencaService {

    private final RegistroPresencaDAO registroPresencaDAO;
    private final AlunoDAO alunoDAO;

    public RegistroPresencaService(RegistroPresencaDAO registroPresencaDAO, AlunoDAO alunoDAO) {
        this.registroPresencaDAO = registroPresencaDAO;
        this.alunoDAO = alunoDAO;
    }

    public RegistroPresenca registrarPresenca(Integer alunoId) {

        Aluno aluno = alunoDAO.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        RegistroPresenca registro = new RegistroPresenca()
                .setAluno(aluno)
                .setDataHoraRegistro(LocalDateTime.now());

        return registroPresencaDAO.save(registro);
    }
}