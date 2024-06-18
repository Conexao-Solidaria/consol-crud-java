package com.consol.api.dto.usuario;

import com.consol.api.entity.Instituicao;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UsuarioConsultaDto {
    private int id;
    private int coordenador;
    private String nomeUsuario;
    private String email;
    private String cpf;
    private Instituicao instituicao;

    @Data
    public static class Instituicao{
        private Integer id;
        private String nome;
        private String cep;
        private String numeroImovel;
        private String descricao;

    }
}
