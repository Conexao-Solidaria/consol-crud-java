package com.consol.api.dto.necessidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NecessidadeCriacaoDto {
    @NotBlank
    private String tipo;
    @NotBlank
    private String descricao;

    @NotNull
    private Integer instituicao_id;
}
