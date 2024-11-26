package com.consol.api.dto.familia;

import com.consol.api.entity.Titular;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class FamiliaConsultaDto {

    private Integer id;
    private String nome;
    private String cep;
    private Integer numeroCasa;
    private Double renda;
    private Byte flagRetirada;
    private LocalDate dataCadastro;
    private List<TitularDto> titulares;
    private List<DespesaDto> despesas;

    @Data
    public static class TitularDto {
        private Integer id;
        private String nome;
        private String rg;
        private String cpf;
        private LocalDate dataNascimento;
        private String telefone1;
        private String telefone2;
        private String estadoCivil;
        private String escolaridade;
        private Byte trabalho;
        private String ocupacao;

    }

    @Data
    public static class DespesaDto {
        private Integer id;
        private String tipo;
        private Double gasto;
    }
}
