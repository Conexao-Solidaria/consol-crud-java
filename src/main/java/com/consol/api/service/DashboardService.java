package com.consol.api.service;

import com.consol.api.entity.Doacao;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

    public Map<String, Integer> ultimosSeisMeses(List<Doacao> doacoes, LocalDate dataAtual) {
        Map<String, Integer> contagemDeDoacoes = new LinkedHashMap <>();
        YearMonth mesReferencia = YearMonth.from(dataAtual);

        for (int i = 6; i >= 0; i--) {
            YearMonth mes = mesReferencia.minusMonths(i);
            String nomeMes = nomeMes(mes.getMonthValue());
            contagemDeDoacoes.put(nomeMes, 0);
        }

        for (Doacao doacao : doacoes) {
            LocalDate dataDoacao = doacao.getDataDoacao().toLocalDate();
            YearMonth mesDaDoacao = YearMonth.from(dataDoacao);

            if (!mesDaDoacao.isBefore(mesReferencia.minusMonths(6)) && !mesDaDoacao.isAfter(mesReferencia)) {
                String nomeMes = nomeMes(dataDoacao.getMonthValue());
                contagemDeDoacoes.put(nomeMes, contagemDeDoacoes.getOrDefault(nomeMes, 0) + 1);
            }
        }

        return contagemDeDoacoes;
    }

    private String nomeMes(Integer numeroMes) {
        switch (numeroMes) {
            case 1:  return "Janeiro";
            case 2:  return "Fevereiro";
            case 3:  return "Março";
            case 4:  return "Abril";
            case 5:  return "Maio";
            case 6:  return "Junho";
            case 7:  return "Julho";
            case 8:  return "Agosto";
            case 9:  return "Setembro";
            case 10: return "Outubro";
            case 11: return "Novembro";
            case 12: return "Dezembro";
            default: return "Mês inválido";
        }

    }

}
