package com.consol.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Instituicao {

    @Id
    private Integer id;
    private String nome;
    private String cep;
    private String numeroImovel;
    private String descricao;
    private byte[] fotoPerfil;

}
