package com.consol.api.dto.instituicao;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class InstituicaoAtualizarDto {
    private String nome;

    @Size(max = 8)
    private String cep;

    private String numeroImovel;

    private String descricao;

    private byte[] fotoPerfil;
}