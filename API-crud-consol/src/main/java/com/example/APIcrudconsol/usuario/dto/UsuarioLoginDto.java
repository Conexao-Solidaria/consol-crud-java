package com.example.APIcrudconsol.usuario.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioLoginDto {

    @NotBlank(message = "O e-mail não pode estar em branco")
    @Email(message = "O e-mail deve ser válido")
    private String email;

    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String senha;

    private Boolean coordenador;
}
