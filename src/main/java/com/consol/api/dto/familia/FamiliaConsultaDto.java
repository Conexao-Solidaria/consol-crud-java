package com.consol.api.dto.familia;

import lombok.Data;

import java.util.List;

@Data
public class FamiliaConsultaDto {

    private String nome;
    private String cep;
    private Integer numeroCasa;
    private Double renda;
    private List<DonatarioDto> donatarios;
    private List<DespesaDto> despesas;

    @Data
    public static class DonatarioDto {
        private Integer id;
        private String estadoCivil;
        private String escolaridade;
        private Boolean trabalhando;
        private String ocupacao;
    }

    @Data
    public static class DespesaDto {
        private Integer id;
        private String tipo;
        private Double gasto;
    }
}
