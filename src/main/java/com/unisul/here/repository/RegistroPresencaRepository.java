package com.unisul.here.repository;

import com.unisul.here.model.RegistroPresenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegistroPresencaRepository extends JpaRepository<RegistroPresenca, Long> {
}
