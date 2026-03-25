package com.unisul.here.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "registro_presenca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroPresenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hora_registro")
    private LocalDateTime horaRegistro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private CadastroUsuario usuario;
}