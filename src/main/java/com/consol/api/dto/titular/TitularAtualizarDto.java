package com.consol.api.dto.titular;

import lombok.Data;

@Data
public class TitularAtualizarDto {

    private String nome;

    private String Sobrenome;

    private String telefone1;

    private String telefone2;

    private String estadoCivil;

    private String escolaridade;

    private Byte trabalhando;

    private String ocupacao;
}
