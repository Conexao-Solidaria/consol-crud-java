package com.consol.api.dto.familia;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FamiliaCadastroDto {
    @NotBlank(message = "O nome da família não pode estar em branco")
    @Size(min = 3)
    private String nome;

    @Size(min = 8, max = 8)
    @NotBlank(message = "O CEP não pode estar em branco")
    private String cep;

    @Positive
    @NotNull(message = "O número da casa não pode ser nulo")
    private Integer numeroCasa;

    @NotNull(message = "A renda não pode ser nula")
    private Double renda;

    @NotNull
    @FutureOrPresent
    private LocalDate dataCadastro;


}