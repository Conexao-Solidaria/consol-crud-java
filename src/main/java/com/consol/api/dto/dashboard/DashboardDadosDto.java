package com.consol.api.dto.dashboard;

import com.consol.api.dto.doacao.DoacaoConsultaDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
