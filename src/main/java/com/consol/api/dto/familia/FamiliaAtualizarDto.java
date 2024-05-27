package com.consol.api.dto.familia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class FamiliaAtualizarDto {
    @NotBlank(message = "O CEP não pode estar em branco")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000")
    private String cep;

    @NotNull(message = "O número da casa não pode ser nulo")
    private Integer numeroCasa;
}
