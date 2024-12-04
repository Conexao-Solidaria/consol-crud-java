package com.consol.api.dto.usuario;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioAtualizarFlagDto {
    @Min(0)
    @Max(1)
    private Byte flagAprovado;
}
