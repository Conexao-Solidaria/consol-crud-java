package com.consol.api.dto.doacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoacaoAtualizarStatusDto {
    @Size(min = 0, max = 1)
    @NotBlank(message = "O status da doação não valida")
    private Integer status;
}
