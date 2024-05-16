package com.consol.api.dto.instituicao;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InstituicaoCadastroDto {
    @NotBlank(message = "O nome da instituição não pode estar em branco")
    private String nomeInstituicao;

    @NotBlank(message = "O CEP não pode estar em branco")
    private String cep;

    @NotBlank(message = "O número do imóvel não pode estar em branco")
    private String numeroImovel;

    private String descricao;

    private byte[] fotoPerfil;
}
