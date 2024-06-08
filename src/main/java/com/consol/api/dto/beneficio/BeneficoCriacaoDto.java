package com.consol.api.dto.beneficio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeneficoCriacaoDto {
    @NotBlank
    private String nome;
    @NotNull
    private Double valor;

}
