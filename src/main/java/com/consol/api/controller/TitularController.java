package com.consol.api.controller;

import com.consol.api.dto.titular.TitularAtualizarDto;
import com.consol.api.dto.titular.TitularCadastroDto;
import com.consol.api.dto.titular.TitularConsultaDto;
import com.consol.api.dto.titular.TitularMapper;
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
    public ResponseEntity<TitularConsultaDto> criar(
            @RequestBody @Valid TitularCadastroDto dto
    ) {
        Donatario donatario = TitularMapper.toEntity(dto);
        Donatario donatarioSalvo = service.salvar(donatario, dto.getIdFamilia());
        TitularConsultaDto titularConsultaDto = TitularMapper.toDto(donatarioSalvo);

        URI uri = URI.create("/donatarios/" + titularConsultaDto.getId());

        return ResponseEntity.created(uri).body(titularConsultaDto);
    }

    @GetMapping
    public ResponseEntity<List<TitularConsultaDto>> listagemDonatario() {
        List<Donatario> donatarios = service.listar();

        if (donatarios.isEmpty()) return ResponseEntity.noContent().build();

        List<TitularConsultaDto> dtos = TitularMapper.toDto(donatarios);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TitularConsultaDto> consultarPorId(
            @PathVariable Integer id
    ) {
        Donatario donatario = service.porId(id);
        TitularConsultaDto dto = TitularMapper.toDto(donatario);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/filtro/por-nome")
    public ResponseEntity<List<TitularConsultaDto>> consultarPorNome(
            @RequestParam String nome
    ) {
        List<Donatario> donatarios = service.listarPorNome(nome);

        if (donatarios.isEmpty()) return ResponseEntity.noContent().build();

        List<TitularConsultaDto> dto = TitularMapper.toDto(donatarios);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<TitularConsultaDto> atualizar(
            @RequestBody @Valid TitularAtualizarDto dto,
            @PathVariable Integer id
    ) {
        Donatario donatario = TitularMapper.toEntity(dto);
        Donatario donatarioAtualizado = service.atualizar(id, donatario);
        TitularConsultaDto titularConsultaDto = TitularMapper.toDto(donatarioAtualizado);

        return ResponseEntity.ok(titularConsultaDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> apagarPorId(
            @PathVariable Integer id
    ) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
