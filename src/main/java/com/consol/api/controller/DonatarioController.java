package com.consol.api.controller;

import com.consol.api.dto.donatario.DonatarioAtualizarDto;
import com.consol.api.dto.donatario.DonatarioCadastroDto;
import com.consol.api.dto.donatario.DonatarioConsultaDto;
import com.consol.api.dto.donatario.DonatarioMapper;
import com.consol.api.entity.Donatario;
import com.consol.api.service.DonatarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/donatarios")
@RequiredArgsConstructor
public class DonatarioController {

    private final DonatarioService service;

    @PostMapping
    public ResponseEntity<DonatarioConsultaDto> criar(
            @RequestBody @Valid DonatarioCadastroDto dto
    ) {
        Donatario donatario = DonatarioMapper.toEntity(dto);
        Donatario donatarioSalvo = service.salvar(donatario, dto.getIdFamilia());
        DonatarioConsultaDto donatarioConsultaDto = DonatarioMapper.toDto(donatarioSalvo);

        URI uri = URI.create("/donatarios/" + donatarioConsultaDto.getId());

        return ResponseEntity.created(uri).body(donatarioConsultaDto);
    }

    @GetMapping
    public ResponseEntity<List<DonatarioConsultaDto>> listagemDonatario() {
        List<Donatario> donatarios = service.listar();

        if (donatarios.isEmpty()) return ResponseEntity.noContent().build();

        List<DonatarioConsultaDto> dtos = DonatarioMapper.toDto(donatarios);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonatarioConsultaDto> consultarPorId(
            @PathVariable Integer id
    ) {
        Donatario donatario = service.porId(id);
        DonatarioConsultaDto dto = DonatarioMapper.toDto(donatario);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/filtro/por-nome")
    public ResponseEntity<List<DonatarioConsultaDto>> consultarPorNome(
            @RequestParam String nome
    ) {
        List<Donatario> donatarios = service.listarPorNome(nome);

        if (donatarios.isEmpty()) return ResponseEntity.noContent().build();

        List<DonatarioConsultaDto> dto = DonatarioMapper.toDto(donatarios);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<DonatarioConsultaDto> atualizar(
            @RequestBody @Valid DonatarioAtualizarDto dto,
            @PathVariable Integer id
    ) {
        Donatario donatario = DonatarioMapper.toEntity(dto);
        Donatario donatarioAtualizado = service.atualizar(id, donatario);
        DonatarioConsultaDto donatarioConsultaDto = DonatarioMapper.toDto(donatarioAtualizado);

        return ResponseEntity.ok(donatarioConsultaDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> apagarPorId(
            @PathVariable Integer id
    ) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}