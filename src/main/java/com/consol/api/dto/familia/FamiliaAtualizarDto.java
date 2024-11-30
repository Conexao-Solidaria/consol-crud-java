package com.consol.api.dto.familia;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FamiliaAtualizarDto {

    private String nome;
    private String cep;
    private Integer numeroCasa;
    private Double renda;
}
