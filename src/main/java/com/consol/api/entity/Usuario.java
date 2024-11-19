package com.consol.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

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
