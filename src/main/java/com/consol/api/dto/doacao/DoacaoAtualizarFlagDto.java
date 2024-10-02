package com.consol.api.dto.doacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoacaoAtualizarFlagDto {
    @Size(min = 0, max = 1)
    @NotBlank(message = "A flag doacao entrega não pode estar em branco")
    private Byte flagDoacaoEntregue;
}