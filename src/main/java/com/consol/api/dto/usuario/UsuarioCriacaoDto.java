package com.consol.api.dto.usuario;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class UsuarioCriacaoDto {

    @PositiveOrZero
    @NotNull
    @Min(0)
    @Max(1)
    private int coordenador;

    @NotBlank
    private String nomeUsuario;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8)
    private String senha;

    @NotBlank
    @CPF
    private String cpf;
}
