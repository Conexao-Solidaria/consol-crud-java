package com.consol.api.dto.dashboard;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.util.Map;


@Data
public class DashboardDadosDto {
    private Integer qtdFamilia;
    private Integer qtdCriancas;
    private Integer cadastrosProximosVencimento;
    private DistribuicaoIdades distribuicaoIdades;

    @Data
    public static class DistribuicaoIdades{
        private Integer zeroDoze;
        private Integer trezeVinteCinco;
        private Integer vinteCincoSessenta;
        private Integer maisSessenta;

    }

}
