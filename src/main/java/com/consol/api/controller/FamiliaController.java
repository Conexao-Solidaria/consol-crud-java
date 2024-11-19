package com.consol.api.controller;

import com.consol.api.dto.familia.*;
import com.consol.api.entity.Familia;
import com.consol.api.service.FamiliaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/familias")
@RequiredArgsConstructor
public class FamiliaController {

    private final FamiliaService service;

    @PostMapping
    public ResponseEntity<FamiliaConsultaDto> criar(
            @RequestBody @Valid FamiliaCadastroDto dto
    ) {
        Familia familia = FamiliaMapper.toEntity(dto);
        Familia familiaSalva = service.salvar(familia);
        FamiliaConsultaDto familiaConsultaDto = FamiliaMapper.toDto(familiaSalva);

        URI uri = URI.create("/familias/" + familiaConsultaDto.getId());

        return ResponseEntity.created(uri).body(familiaConsultaDto);
    }

    @GetMapping
    public ResponseEntity<List<FamiliaConsultaDto>> listar() {
        List<Familia> familias = service.listar();

        if (familias.isEmpty()) return ResponseEntity.noContent().build();

        List<FamiliaConsultaDto> dtos = FamiliaMapper.toDto(familias);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FamiliaConsultaDto> listarPorId(@PathVariable int id) {
        Familia entity = service.porId(id);
        FamiliaConsultaDto familiaConsultaDto = FamiliaMapper.toDto(entity);
        return ResponseEntity.ok().body(familiaConsultaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FamiliaConsultaDto> atualizarEndereco(
            @PathVariable int id,
            @RequestBody @Valid FamiliaAtualizarDto dto
    ) {
        Familia familia = FamiliaMapper.toEntity(dto);
        Familia familiaAtualizada = service.atualizar(id, familia);
        FamiliaConsultaDto familiaConsultaDto = FamiliaMapper.toDto(familiaAtualizada);

        return ResponseEntity.ok(familiaConsultaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(
            @PathVariable int id
    ) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizar-flag/{id}")
    public ResponseEntity<FamiliaConsultaDto> atualizarFlag(
            @PathVariable int id,
            @RequestBody FamiliaAtualizarFlagDto dto
    ) {
        Familia familia = FamiliaMapper.toEntity(dto);
        Familia familiaAtualizada = service.atualizarFlag(id, familia);
        FamiliaConsultaDto familiaConsultaDto = FamiliaMapper.toDto(familiaAtualizada);

        return ResponseEntity.ok(familiaConsultaDto);
    }
}
