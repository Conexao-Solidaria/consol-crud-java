package com.consol.api.entity;

import com.consol.api.entity.Instituicao;
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

    private Byte coordenador;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    private String email;

    private String senha;

    private String cpf;

    @Column(name = "flag_aprovado")
    private Byte flagAprovado;

    @ManyToOne
    @JoinColumn(name = "fk_instituicao")
    private Instituicao instituicao;
}
