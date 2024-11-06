package com.consol.api.dto.beneficio;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BeneficoCriacaoDto {
    @NotBlank
    private String nome;
    @NotNull
    @DecimalMin("1.00")
    @DecimalMax("99999.99")
    private Double valor;

}
