package com.consol.api.dto.doacao;

import com.consol.api.dto.deserializer.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DoacaoConfirmacaoDto {

    @NotNull(message = "A data da doação está nula")
    @FutureOrPresent(message = "A data da doação está inválida")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataDoacao;

}