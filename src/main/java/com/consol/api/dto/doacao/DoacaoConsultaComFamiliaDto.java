package com.consol.api.dto.doacao;

import com.consol.api.dto.familia.FamiliaConsultaDto;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DoacaoConsultaComFamiliaDto {
    private Integer id;
    private String descricao;
    private LocalDateTime dataDoacao;
    private Byte flagDoacaoEntregue;
    private DoacaoConsultaComFamiliaDto.TitularDto titular;

    @Data
    public static class TitularDto {
        private Integer id;
        private String nome;
        private String rg;
        private String cpf;
        private LocalDate dataNascimento;
        private String telefone1;
        private String telefone2;
        private FamiliaDto familia;

        @Data
        public static class FamiliaDto{
            private Integer id;
            private String nome;
            private String cep;
            private Integer numeroCasa;
            private Double renda;
            private Byte flagRetirada;
            private LocalDate dataCadastro;

        }

    }

}
