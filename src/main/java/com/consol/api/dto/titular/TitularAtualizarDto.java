package com.consol.api.dto.donatario;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DonatarioAtualizarDto {

    private String nome;

    private String Sobrenome;

    private String telefone1;

    private String telefone2;

    private String estadoCivil;

    private String escolaridade;

    private Boolean trabalhando;

    private String ocupacao;
}
