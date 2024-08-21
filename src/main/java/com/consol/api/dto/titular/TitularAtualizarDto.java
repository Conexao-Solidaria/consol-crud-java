package com.consol.api.dto.titular;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TitularAtualizarDto {

    private String nome;

    private String Sobrenome;

    private String telefone1;

    private String telefone2;

    private String estadoCivil;

    private String escolaridade;

    private Boolean trabalhando;

    private String ocupacao;
}
