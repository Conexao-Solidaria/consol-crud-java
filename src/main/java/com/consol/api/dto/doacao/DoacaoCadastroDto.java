package com.consol.api.dto.doacao;

import jakarta.validation.constraints.*;
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
    @PastOrPresent(message = "A data da doação está inválida")
    private LocalDate dataDoacao;

    private Integer instituicaoId;

    private Integer donatarioId;
}
