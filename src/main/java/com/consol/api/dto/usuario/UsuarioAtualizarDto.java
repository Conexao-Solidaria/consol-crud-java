package com.consol.api.dto.usuario;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UsuarioAtualizarDto {
    private String nomeUsuario;

    @Email(message = "O e-mail deve ser válido")
    private String email;

    private Boolean coordenador;

    private String cpf;
}
