package com.consol.api.dto.titular;

import com.consol.api.entity.Familia;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TitularConsultaDto {

    private Integer id;
    private String nome;
    private String rg;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone1;
    private String telefone2;
    private String estadoCivil;
    private String escolaridade;
    private Byte trabalhando;
    private String ocupacao;
    private FamiliaDto familia;
    private String referenciaS3;

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
