package com.consol.api.dto.necessidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NecessidadeCriacaoDto {
    @NotBlank
    @NotNull
    private String tipo;
    @NotBlank
    @NotNull
    private String descricao;

    @NotNull
    private Integer instituicao_id;
}
