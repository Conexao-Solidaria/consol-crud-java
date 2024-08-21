package com.consol.api.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioAtualizarFlagDto {
    @Size(min = 1, max = 1)
    @NotBlank(message = "A flag aprovado não pode estar em branco")
    private Integer flagAprovado;
}
