package com.consol.api.dto.usuario;

import lombok.Data;

@Data
public class UsuarioTokenDto {
    private Integer userId;
    private String nome;
    private String email;
    private Byte flagAprovado;
    private Byte coordenador;
    private String token;

}
