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

    private String nome;

    private String cep;

    private String numeroImovel;

    private String descricao;

    private byte[] fotoPerfil;
}
