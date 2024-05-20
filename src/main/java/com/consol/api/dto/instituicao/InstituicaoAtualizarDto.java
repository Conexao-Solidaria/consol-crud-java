package com.consol.api.dto.instituicao;


import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class InstituicaoAtualizarDto {
    private String nome;

    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000")
    private String cep;

    private String numeroImovel;

    private String descricao;

    private byte[] fotoPerfil;
}