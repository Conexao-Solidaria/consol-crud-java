package com.consol.api.dto.doacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoacaoAtualizarDescricaoDto {
    @NotBlank
    @Size(max = 200)
    String descricao;


}
