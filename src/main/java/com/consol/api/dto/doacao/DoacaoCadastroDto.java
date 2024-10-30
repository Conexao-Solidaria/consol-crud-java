package com.consol.api.dto.doacao;

import com.consol.api.dto.deserializer.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DoacaoCadastroDto {

    @NotBlank(message = "A descricao é obrigatória")
    @NotNull(message = "A descrição está nula")
    private String descricao;

    @NotNull(message = "A data da doação está nula")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataDoacao;


}
