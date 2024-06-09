package com.consol.api.dto.beneficio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeneficioAtualizacaoDto {
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private Double valor;
}
