package com.consol.api.dto.familia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FamiliaAtualizarFlagDto {
    @NotBlank(message = "A flag retirada não pode estar em branco")
    private Byte flagRetirada;
}
