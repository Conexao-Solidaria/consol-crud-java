package com.consol.api.dto.doacao;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DoacaoCadastroDto {

    @Positive(message = "O peso está inválido")
    @DecimalMin(value = "6.0", message = "O peso está inválido")
    @NotNull(message = "O peso é obrigatório")
    private Double peso;

    @NotBlank(message = "A descricao é obrigatória")
    @NotNull(message = "A descrição está nula")
    private String descricao;

    @NotNull(message = "A data da doação está nula")
    private LocalDate dataDoacao;

    private Integer instituicaoId;

    private Integer donatarioId;
}
