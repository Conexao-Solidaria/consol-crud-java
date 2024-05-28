package com.consol.api.dto.familia;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FamiliaCadastroDto {
    @NotBlank(message = "O nome da família não pode estar em branco")
    private String nome;

    @Size(max = 8)
    @NotBlank(message = "O CEP não pode estar em branco")
    private String cep;

    @Positive
    @NotNull(message = "O número da casa não pode ser nulo")
    private Integer numeroCasa;

    @NotNull(message = "A renda não pode ser nula")
    private Double renda;
}