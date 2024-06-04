package com.consol.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id;

    private int coordenador;

    private String nomeUsuario;

    private String email;

    private String senha;

    private String cpf;

    @ManyToOne
    private Instituicao instituicao;
}
