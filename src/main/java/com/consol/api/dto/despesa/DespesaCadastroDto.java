package com.consol.api.dto.despesa;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DespesaCadastroDto {

    @NotBlank
    public String tipo;
    @NotNull
    @DecimalMin("1.00")
    @DecimalMax("99999.99")
    public Double gasto;


}
