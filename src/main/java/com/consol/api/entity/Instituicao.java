package com.consol.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instituicao")
    private Integer id;

    @Column(name = "nome_instituicao")
    private String nome;

    @Column(name = "cep", length = 8, nullable = false)
    private String cep;

    private String numeroImovel;

    private String descricao;
}
