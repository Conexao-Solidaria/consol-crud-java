package com.consol.api.dto.doacao;

import com.consol.api.dto.Deserializer.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DoacaoCadastroDto {

    @NotBlank(message = "A descricao é obrigatória")
    @NotNull(message = "A descrição está nula")
    private String descricao;

    @NotNull(message = "A data da doação está nula")
    @PastOrPresent(message = "A data da doação está inválida")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataDoacao;


}
