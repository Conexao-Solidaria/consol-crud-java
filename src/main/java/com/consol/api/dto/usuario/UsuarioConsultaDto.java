package com.consol.api.dto.usuario;

import lombok.Data;

@Data
public class UsuarioConsultaDto {
    private Integer idUsuario;
    private Boolean coordenador;
    private String nomeUsuario;
    private String email;
    private String cpf;
    private Integer fkInstituicao;
}
