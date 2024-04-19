package com.example.APIcrudconsol.instituicao.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class InstituicaoAtualizarDto {
    private String nomeInstituicao;

    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000")
    private String cep;

    private String numeroImovel;

    private String descricao;

    private byte[] fotoPerfil;
}