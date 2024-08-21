package com.consol.api.dto.doacao;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DoacaoCadastroDto {

    @Min(0)
    @Max(1)
    private Integer statusDoacao = 0;

    @NotBlank(message = "A descricao é obrigatória")
    @NotNull(message = "A descrição está nula")
    private String descricao;

    @NotNull(message = "A data da doação está nula")
    @PastOrPresent(message = "A data da doação está inválida")
    private LocalDate dataDoacao;

    private Integer instituicaoId;

    private Integer donatarioId;

    private Integer flagDoacaoEntregue = 0;
}
