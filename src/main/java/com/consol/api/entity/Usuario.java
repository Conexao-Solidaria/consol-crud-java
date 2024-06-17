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
    private Integer id;

    private Integer coordenador;

    private String nomeUsuario;

    private String email;

    private String senha;

    private String cpf;

    @ManyToOne
    @JoinColumn(name = "fk_instituicao")
    private Instituicao instituicao;
}
