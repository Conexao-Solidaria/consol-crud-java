package com.consol.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donatario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private LocalDate dataCadastro;

    private String nome;

    private String Sobrenome;

    private String rg;

    private String cpf;

    private LocalDate dataNascimento;

    private String celular;

    private String telefone;

    private String estadoCivil;

    private String escolaridade;

    private Boolean trabalhando;

    private String ocupacao;

    @OneToMany
    @JoinColumn(name = "fk_beneficio")
    private Beneficio beneficio;

    @ManyToOne
    @JoinColumn(name = "fk_familiar")
    private Familia familia;
}
