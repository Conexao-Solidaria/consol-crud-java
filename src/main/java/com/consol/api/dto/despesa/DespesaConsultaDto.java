package com.consol.api.dto.despesa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DespesaConsultaDto {
    @NotNull
    public Integer id;
    @NotBlank
    public String tipo;
    @NotNull
    public Double gasto;
    @NotNull
    public FamiliaDto familiaDto;

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
