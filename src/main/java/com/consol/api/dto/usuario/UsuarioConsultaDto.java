package com.consol.api.dto.usuario;

import lombok.Data;

@Data
public class UsuarioConsultaDto {
    private Integer idUsuario;
    private Byte coordenador;
    private String nomeUsuario;
    private String email;
    private String cpf;
    private Byte flagAprovado;
    private Integer fkInstituicao;
}
