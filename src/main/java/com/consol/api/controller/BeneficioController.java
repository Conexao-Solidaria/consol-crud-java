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
@RequestMapping("/beneficio")
@RequiredArgsConstructor
public class BeneficioController {

    private final BeneficioService beneficioService;

    @PostMapping("/{idDonatario}")
    private ResponseEntity<BeneficioConsultaDto> salvar(
            @PathVariable int idDonatario,
            @RequestBody @Valid BeneficoCriacaoDto benefico){

        Beneficio entity = BeneficioMapper.toEntity(benefico);
        System.out.println(entity.getNome());
        Beneficio salvo = beneficioService.salvar(entity,idDonatario);
        BeneficioConsultaDto dto = BeneficioMapper.toDto(salvo);
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<BeneficioConsultaDto>> listar(){
        List<Beneficio> entities = beneficioService.listar();

        if (entities.isEmpty()) return ResponseEntity.status(204).build();

        List<BeneficioConsultaDto> dtos = BeneficioMapper.toDto(entities);
        return ResponseEntity.status(200).body(dtos);

    }
}
