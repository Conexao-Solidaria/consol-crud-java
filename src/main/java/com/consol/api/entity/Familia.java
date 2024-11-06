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
public class Familia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_familia")
    private int id;

    private String nome;

    private String cep;

    @Column(name = "numero_casa")
    private Integer numeroCasa;

    private Double renda;

    @Column(name = "flag_retirada")
    private Byte flagRetirada;

    @OneToMany(mappedBy = "familia")
    private List<Titular> titulares;

    @OneToMany(mappedBy = "familia")
    private List<Despesa> despesas;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

}
