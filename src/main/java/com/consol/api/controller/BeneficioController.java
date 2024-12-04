package com.consol.api.controller;

import com.consol.api.dto.beneficio.BeneficioAtualizacaoDto;
import com.consol.api.dto.beneficio.BeneficioConsultaDto;
import com.consol.api.dto.beneficio.BeneficioMapper;
import com.consol.api.dto.beneficio.BeneficoCriacaoDto;
import com.consol.api.entity.Beneficio;
import com.consol.api.service.BeneficioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/beneficios")
@RequiredArgsConstructor
public class BeneficioController {

    private final BeneficioService beneficioService;

    @PostMapping("/titular/{idTitular}")
    public ResponseEntity<BeneficioConsultaDto> salvar(
            @PathVariable int idTitular,
            @RequestBody @Valid BeneficoCriacaoDto benefico){

        Beneficio entity = BeneficioMapper.toEntity(benefico);
        Beneficio salvo = beneficioService.salvar(entity,idTitular);
        BeneficioConsultaDto dto = BeneficioMapper.toDto(salvo);

        URI uri = URI.create("/beneficios/donatario/" + idTitular);

        return ResponseEntity.created(uri).body(dto);
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

    @GetMapping("/titular/{idTitular}")
    public ResponseEntity<List<BeneficioConsultaDto>> listarPorDonarario(@PathVariable int idTitular){
        List<Beneficio> entities = beneficioService.listarPorTitular(idTitular);

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

    @PatchMapping("/{idBeneficio}")
    public ResponseEntity<BeneficioConsultaDto> atualizar(
            @PathVariable int idBeneficio,
            @RequestBody @Valid BeneficioAtualizacaoDto beneficioAtualizacao
    ){

        Beneficio entity = BeneficioMapper.toEntity(beneficioAtualizacao);
        Beneficio atualizado = beneficioService.atualizar(idBeneficio,entity);
        BeneficioConsultaDto dto = BeneficioMapper.toDto(atualizado);
        return ResponseEntity.status(200).body(dto);

    }

    @DeleteMapping("/{idBeneficio}")
    public ResponseEntity deletar(@PathVariable int idBeneficio){
        beneficioService.deletar(idBeneficio);
        return ResponseEntity.status(204).build();
    }
}
