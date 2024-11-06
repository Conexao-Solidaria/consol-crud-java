//package com.consol.api.controller;
//
//import com.consol.api.entity.Instituicao;
//import com.consol.api.dto.instituicao.InstituicaoAtualizarDto;
//import com.consol.api.dto.instituicao.InstituicaoCadastroDto;
//import com.consol.api.dto.instituicao.InstituicaoConsultaDto;
//import com.consol.api.dto.instituicao.InstituicaoMapper;
//import com.consol.api.service.InstituicaoService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.URI;
//import java.util.List;
//
//@RestController
//@RequestMapping("/instituicoes")
//@RequiredArgsConstructor
//public class InstituicaoController {
//
//    private final InstituicaoService service;
//
//    @PostMapping
//    public ResponseEntity<InstituicaoConsultaDto> criar(
//            @RequestBody @Valid InstituicaoCadastroDto dto
//    ){
//        Instituicao instituicao = InstituicaoMapper.toEntity(dto);
//        Instituicao instituicaoSalva = service.criar(instituicao);
//        InstituicaoConsultaDto instituicaoConsultaDto = InstituicaoMapper.toDto(instituicaoSalva);
//
//        URI uri = URI.create("/instituicoes/" + instituicaoConsultaDto.getId());
//
//        return ResponseEntity.created(uri).body(instituicaoConsultaDto);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<InstituicaoConsultaDto>> listagemInstituicao(){
//        List<Instituicao> instituicoes = service.listarInstituicoes();
//
//        if (instituicoes.isEmpty()) return ResponseEntity.noContent().build();
//
//        List<InstituicaoConsultaDto> dtos = InstituicaoMapper.toDto(instituicoes);
//
//        return ResponseEntity.ok(dtos);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<InstituicaoConsultaDto> consultarPorId(
//            @PathVariable Integer id
//    ){
//        Instituicao instituicao = service.consultarPorId(id);
//        InstituicaoConsultaDto dto = InstituicaoMapper.toDto(instituicao);
//
//        return ResponseEntity.ok(dto);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<InstituicaoConsultaDto> atualizar(
//            @RequestBody @Valid InstituicaoAtualizarDto dto,
//            @PathVariable Integer id
//    ){
//        Instituicao instituicao = InstituicaoMapper.toEntity(dto);
//        Instituicao instituicaoAtualizada = service.atualizar(id, instituicao);
//        InstituicaoConsultaDto instituicaoConsultaDto = InstituicaoMapper.toDto(instituicaoAtualizada);
//
//        return ResponseEntity.ok(instituicaoConsultaDto);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> apagarPorId(
//            @PathVariable Integer id
//    ){
//        service.apagarPorId(id);
//        return ResponseEntity.noContent().build();
//    }
//}