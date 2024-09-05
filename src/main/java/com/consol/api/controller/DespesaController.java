package com.consol.api.controller;

import com.consol.api.dto.despesa.DespesaAtualizarDto;
import com.consol.api.dto.despesa.DespesaCadastroDto;
import com.consol.api.dto.despesa.DespesaConsultaDto;
import com.consol.api.dto.despesa.DespesaMapper;
import com.consol.api.entity.Despesa;
import com.consol.api.entity.Familia;
import com.consol.api.entity.exception.RequisicaoIncorretaException;
import com.consol.api.repository.DespesaRepository;
import com.consol.api.repository.FamiliaRepository;
import com.consol.api.service.DespesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/despesas")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService despesaService;

    @PostMapping("/familia/{idFamilia}")
    public ResponseEntity<DespesaConsultaDto> criar(@RequestBody @Valid DespesaCadastroDto despesaCadastroDto,
        @PathVariable int idFamilia
    ) {
        if (despesaCadastroDto == null) throw new RequisicaoIncorretaException("Despesa");

        Despesa entity = DespesaMapper.toEntity(despesaCadastroDto);
        Despesa despesaSalva = despesaService.salvar(entity,idFamilia);

        DespesaConsultaDto dto = DespesaMapper.toDto(despesaSalva);

        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping("/familia/{idFamilia}")
    public ResponseEntity<List<DespesaConsultaDto>> listarPorFamilia(@PathVariable int idFamilia){
        List<Despesa> entities = despesaService.listarPorFamilia(idFamilia);

        if (entities.isEmpty()) return ResponseEntity.status(204).build();

        List<DespesaConsultaDto> dtos = DespesaMapper.despesaToListagemDto(entities);
        return ResponseEntity.status(200).body(dtos);
    }



    @GetMapping("/{id}")
    public ResponseEntity<DespesaConsultaDto> consultarPorId(@PathVariable Integer id) {

        Despesa despesa = despesaService.buscarPorId(id);

        DespesaConsultaDto despesaConsultaDto = new DespesaConsultaDto();

        return ResponseEntity.ok(despesaConsultaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaConsultaDto> atualizar(@RequestBody @Valid DespesaAtualizarDto despesaAtualizarDto,
                                                          @PathVariable Integer id) {

        Despesa despesaBuscada = despesaService.buscarPorId(id);;

        Despesa despesa = DespesaMapper.atualizacaoDtoToDespesa(despesaAtualizarDto);

        despesa.setId(id);
        if (despesa.getTipo() == null) despesa.setTipo(despesaBuscada.getTipo());
        if (despesa.getGasto() == null) despesa.setGasto(despesaBuscada.getGasto());

        Despesa eventoAtualizado = despesaService.salvar(despesa,id);

        DespesaConsultaDto dto = DespesaMapper.despesaToListagemDto(eventoAtualizado);

        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarPorId(@PathVariable Integer id) {

        despesaService.deletarPorId(id);

        return ResponseEntity.noContent().build();
    }
}