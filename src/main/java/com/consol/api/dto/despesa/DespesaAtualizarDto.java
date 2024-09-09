package com.consol.api.dto.despesa;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class DespesaAtualizarDto {
    private String tipo;
    @DecimalMin("1.00")
    @DecimalMax("99999.99")
    private Double gasto;


}
