package com.consol.api.controller;

import com.consol.api.dto.familia.FamiliaAtualizarDto;
import com.consol.api.dto.familia.FamiliaMapper;
import com.consol.api.entity.Familia;
import com.consol.api.service.FamiliaService;
import com.consol.api.dto.familia.FamiliaCadastroDto;
import com.consol.api.dto.familia.FamiliaConsultaDto;
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

    @PutMapping("{id}")
    public ResponseEntity<FamiliaConsultaDto> atualizarEndereco(
            @PathVariable int id,
            @RequestBody FamiliaAtualizarDto dto
    ) {
        Familia familia = FamiliaMapper.toEntity(dto);
        Familia familiaAtualizada = service.atualizar(id, familia);
        FamiliaConsultaDto familiaConsultaDto = FamiliaMapper.toDto(familiaAtualizada);

        return ResponseEntity.ok(familiaConsultaDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> apagar(
            @PathVariable int id
    ) {
        if (!service.deletar(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
