package com.consol.api.dto.doacao;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DoacaoConsultaDto {

    private Integer id;
    private String descricao;
    private LocalDateTime dataDoacao;
//    private InstituicaoDto instituicao;
    private TitularDto donatario;
    private Byte flagDoacaoEntregue;

//    @Data
//    public static class InstituicaoDto {
//        private Integer id;
//        private String nome;
//        private String cep;
//        private String numeroImovel;
//        private String descricao;
//    }

    @Data
    public static class TitularDto {
        private Integer id;
        private String nome;
        private String rg;
        private String cpf;
        private LocalDate dataNascimento;
        private String telefone1;
        private String telefone2;
    }
}
