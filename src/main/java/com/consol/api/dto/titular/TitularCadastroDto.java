package com.consol.api.dto.titular;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
public class TitularCadastroDto {

    @Size(min = 3)
    @NotBlank(message = "O nome do donatário não pode estar em branco")
    private String nome;

    @Size(min = 9, max = 9)
    @NotBlank(message = "O rg do donatário não pode estar em branco")
    private String rg;

    @CPF
    private String cpf;

    @Column(name = "data_nascimento")
    @NotNull(message = "A data de nascimento não pode estar em branco")
    @PastOrPresent
    private LocalDate dataNascimento;

    @NotBlank(message = "O telefone não pode estar em branco")
    private String telefone1;

    private String telefone2;

    @Column(name = "estado_civil")
    @NotBlank(message = "O estado civil não pode estar em branco")
    private String estadoCivil;

    @NotBlank(message = "A escolaridade não pode estar em branco")
    private String escolaridade;

    private Byte trabalhando;

    @Size(max = 45)
    private String ocupacao;

    @Column(name = "fk_familia")
    @NotNull
    @Positive
    private Integer idFamilia;

}
