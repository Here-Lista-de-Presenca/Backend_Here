package com.unisul.here.repository;

import com.unisul.here.model.RegistroPresenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface RegistroPresencaRepository extends JpaRepository<RegistroPresenca, Long> {
    List<RegistroPresenca> findByUsuarioId(Long usuarioId);
}
