package com.consol.api.controller;

import com.consol.api.dto.necessidade.NecessidadeAtualizarDto;
import com.consol.api.dto.necessidade.NecessidadeCriacaoDto;
import com.consol.api.dto.necessidade.NecessidadeListagemDto;
import com.consol.api.dto.necessidade.NecessidadeMapper;
import com.consol.api.entity.Necessidade;
import com.consol.api.service.NecessidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/necessidades")
@RequiredArgsConstructor
public class NecessidadeController {
    private final NecessidadeService necessidadeService;

    @PostMapping()
    public ResponseEntity<NecessidadeListagemDto> criar(
            @RequestBody @Valid NecessidadeCriacaoDto dto) {
        Necessidade necessidade = NecessidadeMapper.toEntity(dto);
        necessidade.setId(dto.getInstituicao_id());

        Necessidade necessidadeSalva = necessidadeService.cadastrar(necessidade);

        NecessidadeListagemDto necessidadeListagemDto = NecessidadeMapper.toDto(necessidadeSalva);

        return ResponseEntity.status(201).body(necessidadeListagemDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NecessidadeListagemDto> buscarId(@PathVariable Integer id){
        Necessidade necessidadeAchada = necessidadeService.buscarPorId(id);

        return ResponseEntity.status(200).body(NecessidadeMapper.toDto(necessidadeAchada));
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<NecessidadeListagemDto>> buscarPorTipo(@RequestParam String tipo){
        List<Necessidade> necessidadesAchadas = necessidadeService.buscarPorTipo(tipo);

        if(necessidadesAchadas.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(NecessidadeMapper.toDtos(necessidadesAchadas));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NecessidadeListagemDto> atualizar(@PathVariable Integer id, @RequestBody NecessidadeAtualizarDto dto){
        Necessidade necessidade = NecessidadeMapper.toEntity(dto);

        return ResponseEntity.status(201).body(NecessidadeMapper.toDto(necessidadeService.atualizar(id, necessidade)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        necessidadeService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<NecessidadeListagemDto>> buscarTodos(){
        List<Necessidade> necessidades = necessidadeService.buscarTodos();

        if(necessidades.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(NecessidadeMapper.toDtos(necessidades));
    }
}
