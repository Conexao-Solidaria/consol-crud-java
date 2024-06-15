package com.consol.api.controller;

import com.consol.api.dto.doacao.DoacaoCadastroDto;
import com.consol.api.dto.doacao.DoacaoConsultaDto;
import com.consol.api.dto.doacao.DoacaoMapper;
import com.consol.api.entity.Doacao;
import com.consol.api.service.DoacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/doacoes")
@RequiredArgsConstructor
public class DoacaoController {

    private final DoacaoService service;

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

    @PostMapping
    private ResponseEntity<DoacaoConsultaDto> criar(
            @RequestBody @Valid DoacaoCadastroDto dto
    ) {
        Doacao doacao = DoacaoMapper.toEntity(dto);
        Doacao doacaoSalva = service.salvar(doacao
                //dto.getInstituicaoId(),
                //dto.getDonatarioId()
        );
        DoacaoConsultaDto doacaoConsultaDto = DoacaoMapper.toDto(doacaoSalva);

        URI uri = URI.create("/doacoes/" + doacaoConsultaDto.getId());

        return ResponseEntity.created(uri).body(doacaoConsultaDto);
    }
}
