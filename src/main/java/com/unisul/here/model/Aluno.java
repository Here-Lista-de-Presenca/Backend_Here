package com.unisul.here.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAluno;
    private String nomeAluno;
    private String registroAcademico;
    // Ativo para deleção lógica
    private boolean ativo;

    public Integer getIdAluno() {
        return idAluno;
    }

    public Aluno setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
        return this;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public Aluno setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
        return this;
    }

    public String getRegistroAcademico() {
        return registroAcademico;
    }

    public Aluno setRegistroAcademico(String registroAcademico) {
        this.registroAcademico = registroAcademico;
        return this;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Aluno setAtivo(boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno that = (Aluno) o;
        return Objects.equals(idAluno, that.idAluno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAluno);
    }
}