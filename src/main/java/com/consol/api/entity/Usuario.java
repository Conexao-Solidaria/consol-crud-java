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

    @Column(name = "coordenador")
    private boolean coordenador;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "flag_aprovado")
    private Integer flagAprovado;

    @ManyToOne
    @JoinColumn(name = "fk_instituicao")
    private Instituicao instituicao;
}
