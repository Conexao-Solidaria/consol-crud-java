package com.consol.api.dto.titular;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
public class TitularAtualizarDto {

    private String nome;

    private String rg;

    private String cpf;

    private LocalDate dataNascimento;

    private String telefone1;

    private String telefone2;

    private String estadoCivil;

    private String escolaridade;

    private Byte trabalhando;

    private String ocupacao;

}
