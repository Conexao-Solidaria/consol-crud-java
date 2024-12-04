package com.consol.api.controller;

import com.consol.api.dto.dashboard.DashboardDadosDto;
import com.consol.api.dto.dashboard.DashboardHistoricoDto;
import com.consol.api.entity.Doacao;
import com.consol.api.service.DashboardService;
import com.consol.api.service.DoacaoService;
import com.consol.api.service.FamiliaService;
import com.consol.api.service.TitularService;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final FamiliaService serviceFamilia;
    private final TitularService serviceTitular;
    private final DoacaoService serviceDoacao;
    private final DashboardService service;


    @GetMapping("/data-atual")
    public ResponseEntity<DashboardDadosDto> dadosDashboard(@RequestParam LocalDate data){
        DashboardDadosDto dto = new DashboardDadosDto();
        dto.setQtdFamilia(serviceFamilia.qtdFamilia());
        dto.setQtdCriancas(serviceTitular.qtdCriancas(data));
        dto.setCadastrosProximosVencimento(serviceFamilia.proximosVencimento(data));

        DashboardDadosDto.DistribuicaoIdades idades = new DashboardDadosDto.DistribuicaoIdades();
        idades.setZeroDoze(serviceTitular.zeroADozeAnos(data));
        idades.setTrezeVinteCinco(serviceTitular.trezeVinteCinco(data));
        idades.setVinteCincoSessenta(serviceTitular.vinteCincoASessenta(data));
        idades.setMaisSessenta(serviceTitular.maisSessenta(data));

        dto.setDistribuicaoIdades(idades);


        return ResponseEntity.status(200).body(dto);

    }

    @GetMapping("/data-base")
    public ResponseEntity<DashboardHistoricoDto> historico(@RequestParam LocalDate data){
        DashboardHistoricoDto dto = new DashboardHistoricoDto();

        List<Doacao> doacoes = serviceDoacao.ultimoSeisMeses(data);
        dto.setQtdDoacoesMes(service.ultimosSeisMeses(doacoes,data));

        return ResponseEntity.status(200).body(dto);
    }

}
