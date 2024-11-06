package com.consol.api.dto.usuario;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class UsuarioCadastroDto {
    @NotBlank()
    private String nomeUsuario;

    @Email()
    private String email;

    @Size(min = 8)
    private String senha;

    @CPF
    private String cpf;

    @Min(0)
    @Max(1)
    private Byte coordenador;

    @Min(0)
    @Max(1)
    private Byte flagAprovado;

    private Integer fkInstituicao;
}
