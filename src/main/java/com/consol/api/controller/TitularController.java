package com.consol.api.controller;

import com.consol.api.dto.titular.TitularAtualizarDto;
import com.consol.api.dto.titular.TitularCadastroDto;
import com.consol.api.dto.titular.TitularConsultaDto;
import com.consol.api.dto.titular.TitularMapper;
import com.consol.api.entity.Titular;
import com.consol.api.service.TitularService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/titulares")
@RequiredArgsConstructor
public class TitularController {

    private final TitularService service;

    @PostMapping
    public ResponseEntity<TitularConsultaDto> criar(
            @RequestBody @Valid TitularCadastroDto dto
    ) {
        Titular titular = TitularMapper.toEntity(dto);
        Titular titularSalvo = service.salvar(titular, dto.getIdFamilia());
        TitularConsultaDto titularConsultaDto = TitularMapper.toDto(titularSalvo);

        URI uri = URI.create("/titulares/" + titularConsultaDto.getId());

        return ResponseEntity.created(uri).body(titularConsultaDto);
    }

    @GetMapping
    public ResponseEntity<List<TitularConsultaDto>> listagemTitular() {
        List<Titular> titulars = service.listar();

        if (titulars.isEmpty()) return ResponseEntity.noContent().build();

        List<TitularConsultaDto> dtos = TitularMapper.toDto(titulars);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TitularConsultaDto> consultarPorId(
            @PathVariable Integer id
    ) {
        Titular titular = service.porId(id);
        TitularConsultaDto dto = TitularMapper.toDto(titular);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/filtro/por-nome")
    public ResponseEntity<List<TitularConsultaDto>> consultarPorNome(
            @RequestParam String nome
    ) {
        List<Titular> titulars = service.listarPorNome(nome);

        if (titulars.isEmpty()) return ResponseEntity.noContent().build();

        List<TitularConsultaDto> dto = TitularMapper.toDto(titulars);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<TitularConsultaDto> atualizar(
            @RequestBody @Valid TitularAtualizarDto dto,
            @PathVariable Integer id
    ) {
        Titular titular = TitularMapper.toEntity(dto);
        Titular titularAtualizado = service.atualizar(id, titular);
        TitularConsultaDto titularConsultaDto = TitularMapper.toDto(titularAtualizado);

        return ResponseEntity.ok(titularConsultaDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> apagarPorId(
            @PathVariable Integer id
    ) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/foto/{id}", consumes = "image/*")
    public ResponseEntity<Void> atualizarFoto(
            @PathVariable final Integer id,
            @RequestBody final byte[] referenciaArquivoFoto) {
        service.atualizarFoto(id, referenciaArquivoFoto);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/foto/{id}", produces = "image/png")
    public ResponseEntity<byte[]> getFoto(@PathVariable final Integer id) {
        byte[] foto = service.getFoto(id);

        return ResponseEntity.ok(foto);
    }
}