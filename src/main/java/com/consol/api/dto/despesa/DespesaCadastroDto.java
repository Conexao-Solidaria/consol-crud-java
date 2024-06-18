package com.consol.api.dto.despesa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DespesaCadastroDto {

    @NotBlank
    public String tipo;
    @NotNull
    public Double gasto;
    @NotNull
    public DespesaConsultaDto.FamiliaDto familiaDto;

    @Data
    public static class FamiliaDto{
        @NotNull
        private int id;
        @NotBlank
        private String nome;
        @NotBlank
        private String cep;
        @NotBlank
        private Integer numeroCasa;
        @NotNull
        private Double renda;
    }
}
