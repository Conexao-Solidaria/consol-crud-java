package com.consol.api.controller;

import com.consol.api.dto.despesa.DespesaAtualizarDto;
import com.consol.api.dto.despesa.DespesaCadastroDto;
import com.consol.api.dto.despesa.DespesaConsultaDto;
import com.consol.api.dto.despesa.DespesaMapper;
import com.consol.api.entity.Despesa;
import com.consol.api.entity.Familia;
import com.consol.api.entity.exception.RequisicaoIncorretaException;
import com.consol.api.hashTable.HashTable;
import com.consol.api.repository.DespesaRepository;
import com.consol.api.repository.FamiliaRepository;
import com.consol.api.service.DespesaService;
import jakarta.validation.Constraint;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/despesas")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService despesaService;

    private HashTable hashTable = new HashTable(3);

    @PostMapping("/familia/{idFamilia}")
    public ResponseEntity<DespesaConsultaDto> criar(@RequestBody @Valid DespesaCadastroDto despesaCadastroDto,
        @PathVariable int idFamilia
    ) {
        if (despesaCadastroDto == null) throw new RequisicaoIncorretaException("Despesa");

        Despesa entity = DespesaMapper.toEntity(despesaCadastroDto);
        Despesa despesaSalva = despesaService.salvar(entity,idFamilia);

        DespesaConsultaDto dto = DespesaMapper.toDto(despesaSalva);

        hashTable.insere(dto);

        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping()
    public ResponseEntity<List<DespesaConsultaDto>> listar(){
        List<Despesa> entities = despesaService.listar();

        if (entities.isEmpty()) return ResponseEntity.status(204).build();

        List<DespesaConsultaDto> dtos = DespesaMapper.toDto(entities);
        return ResponseEntity.status(200).body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaConsultaDto> consultarPorId(@PathVariable Integer id) {

        Despesa despesa = despesaService.buscarPorId(id);

        DespesaConsultaDto despesaConsultaDto = DespesaMapper.toDto(despesa);

        return ResponseEntity.ok(despesaConsultaDto);
    }

    @GetMapping("/familia/{idFamilia}")
    public ResponseEntity<List<DespesaConsultaDto>> listarPorFamilia(@PathVariable int idFamilia){
        List<Despesa> entities = despesaService.listarPorFamilia(idFamilia);

        if (entities.isEmpty()) return ResponseEntity.status(204).build();

        List<DespesaConsultaDto> dtos = DespesaMapper.toDto(entities);
        return ResponseEntity.status(200).body(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaConsultaDto> atualizar(@RequestBody @Valid DespesaAtualizarDto despesaAtualizarDto,
                                                        @PathVariable Integer id) {

        if (despesaAtualizarDto == null) return ResponseEntity.status(400).build();

        Despesa entity = DespesaMapper.toEntity(despesaAtualizarDto);
        Despesa despesaAtualizada = despesaService.atualizarDespesa(entity,id);
        DespesaConsultaDto dto = DespesaMapper.toDto(despesaAtualizada);

        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarPorId(@PathVariable Integer id) {
        despesaService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pesquisaHash/{valor}")
    public ResponseEntity<List<DespesaConsultaDto>> pesquisaHash(@PathVariable double valor){
        List<DespesaConsultaDto> retorno = hashTable.pesquisaValor(valor);

        if(retorno.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(retorno);
    }
}