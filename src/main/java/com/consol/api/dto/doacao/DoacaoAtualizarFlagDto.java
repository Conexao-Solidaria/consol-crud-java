package com.consol.api.dto.doacao;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoacaoAtualizarFlagDto {

    @NotNull
    private Byte flagDoacaoEntregue;

    @AssertTrue()
    public boolean isFlagDoacaoEntregueValido() {
        return flagDoacaoEntregue != null && (flagDoacaoEntregue == 0 || flagDoacaoEntregue == 1);
    }
}