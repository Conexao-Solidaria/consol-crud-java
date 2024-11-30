package com.consol.api.dto.titular;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;

@Data
public class TitularAtualizarFamiliaDto {
    @NotNull
    @Positive
    private Integer idFamilia;
}
