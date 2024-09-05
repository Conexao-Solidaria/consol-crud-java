package com.consol.api.dto.despesa;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DespesaCadastroDto {

    @NotBlank
    public String tipo;
    @NotNull
    @Min(1)
    public Double gasto;


}
