package com.consol.api.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioAtualizarFlagDto {
    @Size(min = 1, max = 1)
    @NotNull
    private Byte flagAprovado;
}
