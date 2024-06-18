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
public class Donatario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_donatario")
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

    @OneToMany
    @JoinColumn(name = "fk_beneficio")
    private List<Beneficio> beneficio;

    @ManyToOne
    @JoinColumn(name = "fk_familiar")
    private Familia familia;
}