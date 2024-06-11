package com.consol.api.dto.necessidade;

import lombok.Data;

@Data
public class NecessidadeListagemDto {
    private Integer id;
    private String tipo;
    private String descricao;
    private InstituicaoDtoNecessidade instituicao;

    @Data
    public static class InstituicaoDtoNecessidade{
        private Integer id;
        private String nome;
        private String cep;
        private String numeroImovel;
        private String descricao;
        private byte[] fotoPerfil;
    }
}
