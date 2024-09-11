package com.consol.api.dto.beneficio;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BeneficioAtualizacaoDto {

    private String nome;

    @DecimalMax("99999.00")
    @DecimalMin("1.00")
    private Double valor;
}
