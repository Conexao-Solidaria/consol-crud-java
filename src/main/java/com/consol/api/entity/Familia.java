package com.consol.api.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private Integer numeroCasa;

    private Double renda;

    private Integer flagRetirada;

    @OneToMany(mappedBy = "familia")
    private List<Titular> titulars;

    @OneToMany(mappedBy = "familia")
    private List<Despesa> despesas;
}
