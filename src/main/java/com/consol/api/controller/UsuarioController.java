package com.consol.api.controller;

import com.consol.api.dto.usuario.UsuarioAtualizacaoDto;
import com.consol.api.dto.usuario.UsuarioConsultaDto;
import com.consol.api.dto.usuario.UsuarioCriacaoDto;
import com.consol.api.dto.usuario.UsuarioMapper;
import com.consol.api.entity.Usuario;
import com.consol.api.service.UsuarioService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity <List<UsuarioConsultaDto>> listar() {
        List<Usuario> entities = usuarioService.listar();

        if (entities.isEmpty()) return ResponseEntity.status(204).build();

        List<UsuarioConsultaDto> dtos = UsuarioMapper.toDto(entities);
        return ResponseEntity.status(200).body(dtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioConsultaDto> listarPorId(@PathVariable int id){
        Usuario entity = usuarioService.listarPorId(id);
        UsuarioConsultaDto dto = UsuarioMapper.toDto(entity);

        return ResponseEntity.status(200).body(dto);

    }

    @GetMapping("/instituicao/{id}")
    public ResponseEntity<List<UsuarioConsultaDto>> listarPorInstituicao(@PathVariable int id){
        List<Usuario> entities = usuarioService.porIdInstuicao(id);

        if (entities.isEmpty()) return ResponseEntity.status(204).build();

        List<UsuarioConsultaDto> dto = UsuarioMapper.toDto(entities);
        return ResponseEntity.status(200).body(dto);

    }

    @PostMapping("/instituicao/{id}")
    public ResponseEntity<UsuarioConsultaDto> criar(
            @RequestBody @Valid UsuarioCriacaoDto usuarioCriar,
            @PathVariable int id
            ){

        Usuario entity = UsuarioMapper.toEntity(usuarioCriar);
        Usuario salvo = usuarioService.salvar(entity,id);
        UsuarioConsultaDto dto = UsuarioMapper.toDto(salvo);

        return ResponseEntity.status(201).body(dto);

    }


    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioConsultaDto> atualizar(
            @PathVariable int id,
            @RequestBody @Valid UsuarioAtualizacaoDto usuarioAtualizacaoDto
    ){

        Usuario entity = UsuarioMapper.toEntity(usuarioAtualizacaoDto);
        Usuario atualizado = usuarioService.atualizar(id,entity);
        UsuarioConsultaDto dto = UsuarioMapper.toDto(atualizado);

        return ResponseEntity.status(201).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable int id){
        usuarioService.deletar(id);
        return ResponseEntity.status(204).build();
    }

}
