package com.consol.api.controller;

import com.consol.api.dto.beneficio.BeneficioConsultaDto;
import com.consol.api.dto.beneficio.BeneficioMapper;
import com.consol.api.dto.beneficio.BeneficoCriacaoDto;
import com.consol.api.entity.Beneficio;
import com.consol.api.service.BeneficioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficios")
@RequiredArgsConstructor
public class BeneficioController {

    // enity = trocado de id para idBenefico, para seguir padrão do banco
    //  RequestMapping = trocado de /benefico para /beneficios para seguir boa prática
    // Alterar de donatário Repository para donatario service, após a service de donatário estar completa
    // Buscar por Donatario =  trocado do nome do donatário para o id, pois pode ter mais de um donatário com o mesmo nome
    // Busca por Familia =  trocado de nome família para o id, mesma questão do busca por donatário
    // atualizar beneficio

    private final BeneficioService beneficioService;

    @PostMapping("/donatario/{idDonatario}")
    public ResponseEntity<BeneficioConsultaDto> salvar(
            @PathVariable int idDonatario,
            @RequestBody @Valid BeneficoCriacaoDto benefico){

        Beneficio entity = BeneficioMapper.toEntity(benefico);
        Beneficio salvo = beneficioService.salvar(entity,idDonatario);
        BeneficioConsultaDto dto = BeneficioMapper.toDto(salvo);
        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<BeneficioConsultaDto>> listar(){
        List<Beneficio> entities = beneficioService.listar();

        if (entities.isEmpty()) return ResponseEntity.status(204).build();

        List<BeneficioConsultaDto> dtos = BeneficioMapper.toDto(entities);
        return ResponseEntity.status(200).body(dtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BeneficioConsultaDto> listarPorId(@PathVariable int id){
        Beneficio entity = beneficioService.listarPorId(id);
        BeneficioConsultaDto dto = BeneficioMapper.toDto(entity);
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/donatario/{idDonatario}")
    public ResponseEntity<List<BeneficioConsultaDto>> listarPorDonarario(@PathVariable int idDonatario){
        List<Beneficio> entities = beneficioService.listarPorDonatario(idDonatario);

        if (entities.isEmpty()) return ResponseEntity.status(204).build();

        List<BeneficioConsultaDto> dtos = BeneficioMapper.toDto(entities);
        return ResponseEntity.status(200).body(dtos);

    }

    @GetMapping("/familia/{idFamilia}")
    public ResponseEntity<List<BeneficioConsultaDto>> listarPorFamilia(@PathVariable int idFamilia){
        List<Beneficio> entities = beneficioService.listarPorFamilia(idFamilia);

        if (entities.isEmpty()) return ResponseEntity.status(204).build();

        List<BeneficioConsultaDto> dtos = BeneficioMapper.toDto(entities);
        return ResponseEntity.status(200).body(dtos);
    }


}
