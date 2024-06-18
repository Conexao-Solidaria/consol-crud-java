package com.consol.api.dto.doacao;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DoacaoConsultaDto {

    private Integer id;
    private Double peso;
    private String descricao;
    private LocalDate dataDoacao;
    private InstituicaoDto instituicao;
    private DonatarioDto donatario;

    @Data
    public static class InstituicaoDto {
        private Integer id;
        private String nome;
        private String cep;
        private String numeroImovel;
        private String descricao;
    }

    @Data
    public static class DonatarioDto {
        private Integer id;
        private LocalDate dataCadastro;
        private String nome;
        private String rg;
        private String cpf;
        private LocalDate dataNascimento;
        private String telefone1;
        private String telefone2;
    }
}
