package com.example.APIcrudconsol.usuario.dto;

import lombok.Data;

@Data
public class UsuarioTokenDto {
    private int userId;
    private String nome;
    private String email;
    private String token;
}
