package com.consol.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Titular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_titular")
    private Integer id;

    private LocalDate dataCadastro;

    private String nome;

    private String Sobrenome;

    private String rg;

    private String cpf;

    private LocalDate dataNascimento;

    private String telefone1;

    private String telefone2;

    private String estadoCivil;

    private String escolaridade;

    private Boolean trabalhando;

    private String ocupacao;

    @ManyToOne
    @JoinColumn(name = "fk_familia")
    private Familia familia;
}
