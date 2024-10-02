package com.consol.api.controller;

import com.consol.api.dto.doacao.*;
import com.consol.api.dto.instituicao.InstituicaoAtualizarDto;
import com.consol.api.dto.instituicao.InstituicaoConsultaDto;
import com.consol.api.dto.instituicao.InstituicaoMapper;
import com.consol.api.entity.Despesa;
import com.consol.api.entity.Doacao;
import com.consol.api.entity.Instituicao;
import com.consol.api.service.DoacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doacoes")
@RequiredArgsConstructor
public class DoacaoController {

    private final DoacaoService service;

    @PostMapping("/titular/{id}/instituicao/{idInstituicao}") // vai ser sempre 1
    private ResponseEntity<DoacaoConsultaDto> criar(
            @RequestBody @Valid DoacaoCadastroDto dto,
            @PathVariable int id,
            @PathVariable int idInstituicao

    ) {

        Doacao doacao = DoacaoMapper.toEntity(dto);
        Doacao doacaoSalva = service.salvar(doacao,id, idInstituicao);

        DoacaoConsultaDto doacaoConsultaDto = DoacaoMapper.toDto(doacaoSalva);

        URI uri = URI.create("/doacoes/" + doacaoConsultaDto.getId());

        return ResponseEntity.created(uri).body(doacaoConsultaDto);
    }


    @GetMapping
    private ResponseEntity<List<DoacaoConsultaDto>> listar() {
        List<Doacao> doacoes = service.listar();

        if (doacoes.isEmpty()) return ResponseEntity.noContent().build();

        List<DoacaoConsultaDto> dtos = DoacaoMapper.toDto(doacoes);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    private ResponseEntity<DoacaoConsultaDto> porData(
            @PathVariable Integer id
    ) {
        Doacao doacao = service.listarPorId(id);
        DoacaoConsultaDto dto = DoacaoMapper.toDto(doacao);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/filtro/por-data")
    private ResponseEntity<List<DoacaoConsultaDto>> porData(
            @RequestParam LocalDate data
    ) {
        List<Doacao> doacoes = service.listarPorData(data);

        if (doacoes.isEmpty()) return ResponseEntity.noContent().build();

        List<DoacaoConsultaDto> dtos = DoacaoMapper.toDto(doacoes);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/filtro/por-periodo")
    private ResponseEntity<List<DoacaoConsultaDto>> porPeriodo(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim
    ) {
        List<Doacao> doacoes = service.listarPorPeriodo(dataInicio, dataFim);

        if (doacoes.isEmpty()) return ResponseEntity.noContent().build();

        List<DoacaoConsultaDto> dtos = DoacaoMapper.toDto(doacoes);

        return ResponseEntity.ok(dtos);
    }


    @PutMapping("/atualizar-flag/{id}")
    public ResponseEntity<DoacaoConsultaDto> atualizarFlag(
            @RequestBody @Valid DoacaoAtualizarFlagDto dto,
            @PathVariable Integer id
    ){
        Doacao doacao = DoacaoMapper.toEntity(dto);
        Doacao doacaoAtualizada = service.atualizarFlag(id, doacao);
        DoacaoConsultaDto doacaoConsultaDto = DoacaoMapper.toDto(doacaoAtualizada);

        return ResponseEntity.ok(doacaoConsultaDto);
    }

    @PutMapping("/atualizar-status/{id}")
    public ResponseEntity<DoacaoConsultaDto> atualizarStatus(
            @RequestBody @Valid DoacaoAtualizarStatusDto dto,
            @PathVariable Integer id
    ){
        Doacao doacao = DoacaoMapper.toEntity(dto);
        Doacao doacaoAtualizada = service.atualizarStatus(id, doacao);
        DoacaoConsultaDto doacaoConsultaDto = DoacaoMapper.toDto(doacaoAtualizada);

        return ResponseEntity.ok(doacaoConsultaDto);
    }
}
