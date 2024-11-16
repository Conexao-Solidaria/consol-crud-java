package com.consol.api.dto.dashboard;

import lombok.Data;

import java.util.Map;

@Data
public class DashboardHistoricoDto {
    private Map <String, Integer> qtdDoacoesMes;
}
