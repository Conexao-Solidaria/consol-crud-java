package com.consol.api.dto.titular;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TitularCadastroDto {

    private LocalDate dataCadastro;

    @NotBlank(message = "O nome do donatário não pode estar em branco")
    private String nome;

    @NotBlank(message = "O rg do donatário não pode estar em branco")
    private String rg;

    @Size(min = 8, max = 8)
    @NotBlank(message = "O cpf do donatário não pode estar em branco")
    private String cpf;

    @NotBlank(message = "A data de nascimento não pode estar em branco")
    private LocalDate dataNascimento;

    @NotBlank(message = "O telefone não pode estar em branco")
    private String telefone1;

    private String telefone2;

    @NotBlank(message = "O estado civil não pode estar em branco")
    private String estadoCivil;

    @NotBlank(message = "A escolaridade não pode estar em branco")
    private String escolaridade;

    private Boolean trabalhando;

    private String ocupacao;

    private Integer idFamilia;
}
