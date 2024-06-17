package com.consol.api.dto.familia;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class FamiliaAtualizarDto {
    @Size(min = 8, max = 8)
    @NotBlank(message = "O CEP não pode estar em branco")
    private String cep;

    @Positive
    @NotNull(message = "O número da casa não pode ser nulo")
    private Integer numeroCasa;
}
