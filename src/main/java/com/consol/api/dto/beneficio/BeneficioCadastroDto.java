package com.consol.api.dto.beneficio;

import com.consol.api.entity.Donatario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BeneficioCadastroDto {

    @NotBlank
    private String nome;
    @NotNull
    private Double valor;

    @NotNull
    private Donatario donatario;
}
