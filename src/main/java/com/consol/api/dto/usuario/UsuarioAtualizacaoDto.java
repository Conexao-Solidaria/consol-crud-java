package com.consol.api.dto.usuario;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class UsuarioAtualizacaoDto {

    @PositiveOrZero
    @Min(0)
    @Max(1)
    private int coordenador;

    @Email
    private String email;

    @Size(min = 8)
    private String senha;

}
