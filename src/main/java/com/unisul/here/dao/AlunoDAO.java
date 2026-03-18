package com.unisul.here.dao;

import com.unisul.here.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoDAO extends JpaRepository<Aluno, Integer> {

}
