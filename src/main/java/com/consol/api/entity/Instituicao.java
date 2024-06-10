package com.consol.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Instituicao {

    @Id
    private Integer id;
    private String nome;
    private String cep;
    private String numeroImovel;
    private String descricao;
    private byte[] fotoPerfil;

}
