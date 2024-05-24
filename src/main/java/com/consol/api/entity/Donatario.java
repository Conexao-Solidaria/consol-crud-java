package com.consol.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
//@Data
@Builder
public class Donatario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idDonatario;

    private LocalDate dataCadastro;

    private String nome;

    private String rg;

    private String cpf;

    private LocalDate dataNascimento;

    private String celular;

    private String telefone;

//    @Column(name = "cep")
//    private String cep;
//
//    @Column(name = "numerocasa")
//    private int numeroCasa;

    private String estadoCivil;

    private String escolaridade;

    private Boolean trabalhando;

    private String ocupacao;

    @ManyToOne
    @JoinColumn(name = "fk_familiar")
    private Familia familia;
}
