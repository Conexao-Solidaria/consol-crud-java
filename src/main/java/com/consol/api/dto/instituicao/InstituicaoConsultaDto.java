package com.consol.api.dto.instituicao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class InstituicaoConsultaDto {
    private int idInstituicao;

    @NotBlank(message = "O nome da instituição não pode estar em branco")
    private String nomeInstituicao;

    @NotBlank(message = "O CEP não pode estar em branco")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000")
    private String cep;

    @NotBlank(message = "O número do imóvel não pode estar em branco")
    private String numeroImovel;

    private String descricao;

    private byte[] fotoPerfil;
}
