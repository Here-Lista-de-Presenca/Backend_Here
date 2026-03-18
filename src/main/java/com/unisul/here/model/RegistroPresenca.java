package com.unisul.here.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_presenca")
public class RegistroPresenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hora_registro")
    private LocalDateTime horaRegistro;

    public RegistroPresenca() {}

    public RegistroPresenca(LocalDateTime horaRegistro) {
        this.horaRegistro = horaRegistro;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(LocalDateTime horaRegistro) {
        this.horaRegistro = horaRegistro;
    }
}