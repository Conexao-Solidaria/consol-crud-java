package com.consol.api.controller;

import com.consol.api.dto.usuario.*;
import com.consol.api.entity.Usuario;
import com.consol.api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;
    // private FilaCircular fila = new FilaCircular(100);

    @PostMapping("/instituicao/{idInstituicao}")
    public ResponseEntity<UsuarioConsultaDto> criar(
            @PathVariable int idInstituicao,
            @RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto
    ){

        Usuario entity = UsuarioMapper.toEntity(usuarioCadastroDto);
        Usuario usuarioCadastrado = service.criar(entity, idInstituicao);
        UsuarioConsultaDto dto = UsuarioMapper.toDto(usuarioCadastrado);

        return ResponseEntity.status(201).body(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioToken = service.autenticar(usuarioLoginDto);
        return ResponseEntity.ok(usuarioToken);
    }


    @GetMapping
    public ResponseEntity<List<UsuarioConsultaDto>> listagemUsuarios(){
        List<Usuario> entities = service.listar();

        if (entities.isEmpty()) return ResponseEntity.status(204).build();

        List<UsuarioConsultaDto> dtos = UsuarioMapper.toDto(entities);
        return ResponseEntity.status(200).body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioConsultaDto> consultarPorId(@PathVariable Integer id){
        Usuario entity = service.porId(id);
        UsuarioConsultaDto dto = UsuarioMapper.toDto(entity);

        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarPorId( @PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("atualizar-flag/{id}")
    public ResponseEntity<UsuarioConsultaDto> atualizarFlag(
            @PathVariable int id,
            @RequestBody UsuarioAtualizarFlagDto usuarioAtualizarFlagDto
    ){
        Usuario entity = UsuarioMapper.toEntity(usuarioAtualizarFlagDto);
        Usuario usuarioAtualizado = service.atualizarFlag(id,entity);
        UsuarioConsultaDto dto = UsuarioMapper.toDto(usuarioAtualizado);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/atualizar-coordenador/{id}")
    public ResponseEntity<UsuarioConsultaDto> atualizarCoordenador(
            @RequestBody UsuarioAtualizarCoordenadorDto atualizarCoordenadorDto,
            @PathVariable int id
    ){
        Usuario entity = UsuarioMapper.toEntity(atualizarCoordenadorDto);
        Usuario usuarioAtualizado = service.atualizarCoordenador(id,entity);
        UsuarioConsultaDto dto = UsuarioMapper.toDto(usuarioAtualizado);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/atualizar-flag-coordenador/{id}")
    public ResponseEntity<UsuarioConsultaDto> atualizarFlagCoordenador(
            @RequestBody UsuarioAtualizarFlagCoordenadorDto atualizarFlagCoordenadorDto,
            @PathVariable int id
    ){
        Usuario entity = UsuarioMapper.toEntity(atualizarFlagCoordenadorDto);
        Usuario usuarioAtualizado = service.atualizarFlagCoordenador(id,entity);
        UsuarioConsultaDto dto = UsuarioMapper.toDto(usuarioAtualizado);

        return ResponseEntity.ok(dto);
    }

//
//
//    //@GetMapping("/fila")
//    //public UsuarioConsultaDto pegarUltimaAdicao(){
//    //    Optional<Usuario> usuarioOptional = usuarioRepository.findById((fila.peek()));
//    //    return usuarioOptional.map(UsuarioMapper::usuarioParaConsultaDto).orElse(null);
//    //}
//

}