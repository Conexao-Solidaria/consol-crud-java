package com.consol.api.dto.usuario;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UsuarioAtualizarFlagCoordenadorDto {
    @Min(0)
    @Max(1)
    private Byte coordenador;

    @Min(0)
    @Max(1)
    private Byte flagAprovado;

}
