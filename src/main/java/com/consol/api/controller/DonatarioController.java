package com.consol.api.controller;

import com.consol.api.dto.donatario.DonatarioAtualizarDto;
import com.consol.api.dto.donatario.DonatarioCadastroDto;
import com.consol.api.dto.donatario.DonatarioConsultaDto;
import com.consol.api.dto.donatario.DonatarioMapper;
import com.consol.api.dto.familia.FamiliaConsultaDto;
import com.consol.api.entity.Donatario;
import com.consol.api.entity.Familia;
import com.consol.api.repository.DonatarioRepository;
import com.consol.api.repository.FamiliaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/donatarios")
public class DonatarioController {

    @Autowired
    DonatarioRepository donatarioRepository;

    @Autowired
    FamiliaRepository familiaRepository;

    @PostMapping
    public ResponseEntity<DonatarioConsultaDto> criar(@RequestBody @Valid DonatarioCadastroDto donatarioCadastroDto) {

        if (donatarioCadastroDto == null) return ResponseEntity.status(400).build();

        Familia familia = familiaRepository.findByNameAndCepEquals(donatarioCadastroDto.getCepFamilia(), donatarioCadastroDto.getNomeFamilia());

        if (familia == null) {
            return ResponseEntity.status(404).body(null);
        }

        Donatario donatarioSalvar = DonatarioMapper.cadastroDtoToDonatario(donatarioCadastroDto);
        donatarioSalvar.setFamilia(familia);

        Donatario donatarioSalvo = donatarioRepository.save(donatarioSalvar);

        DonatarioConsultaDto donatarioConsultaDto = DonatarioMapper.donatarioToListagemDto(donatarioSalvo);

        return ResponseEntity.status(201).body(donatarioConsultaDto);
    }

    @GetMapping
    public ResponseEntity<List<DonatarioConsultaDto>> listagemDonatario() {
        List<Donatario> donatarios = donatarioRepository.findAll();

        if (donatarios.isEmpty()) return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(DonatarioMapper.listagemDtoToDonatario(donatarios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonatarioConsultaDto> consultarPorId(@PathVariable Integer id) {
        Optional<Donatario> donatarioOptional = donatarioRepository.findById(id);

        if (!donatarioOptional.isPresent()) {
            return ResponseEntity.status(404).body(null); // Donatário não encontrado
        }

        Donatario donatario = donatarioOptional.get();
        Familia familia = donatario.getFamilia();

        FamiliaConsultaDto familiaDto = new FamiliaConsultaDto();
        DonatarioConsultaDto donatarioConsultaDto = new DonatarioConsultaDto();

        return ResponseEntity.ok(donatarioConsultaDto);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<DonatarioConsultaDto>> consultarPorNome(@RequestParam String nome) {
        List<Donatario> donatarios = donatarioRepository.findByNameLike(nome);

        if (donatarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List<DonatarioConsultaDto> donatarioConsultaDto = DonatarioMapper.listagemDtoToDonatario(donatarios);
        return ResponseEntity.status(200).body(donatarioConsultaDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<DonatarioConsultaDto> atualizar(@RequestBody @Valid DonatarioAtualizarDto donatarioAtualizarDto,
                                                          @PathVariable Integer id) {

        Optional<Donatario> donatarioBuscadoOpt = donatarioRepository.findById(id);

        if (donatarioBuscadoOpt.isEmpty()) return ResponseEntity.status(404).build();

        Donatario donatarioBuscado = donatarioBuscadoOpt.get();

        Donatario donatario = DonatarioMapper.atualizacaoDtoToDonatario(donatarioAtualizarDto);

        donatario.setId(id);
        if (donatario.getNome() == null) donatario.setNome(donatarioBuscado.getNome());
        if (donatario.getTelefone1() == null) donatario.setTelefone1(donatarioBuscado.getTelefone1());
        if (donatario.getTelefone2() == null) donatario.setTelefone2(donatarioBuscado.getTelefone2());
        if (donatario.getEstadoCivil() == null) donatario.setEstadoCivil(donatarioBuscado.getEstadoCivil());
        if (donatario.getEscolaridade() == null) donatario.setEscolaridade(donatarioBuscado.getEscolaridade());
        if (donatario.getTrabalhando() == null) donatario.setTrabalhando(donatarioBuscado.getTrabalhando());
        if (donatario.getOcupacao() == null) donatario.setOcupacao(donatarioBuscado.getOcupacao());

        Donatario eventoAtualizado = donatarioRepository.save(donatario);

        DonatarioConsultaDto dto = DonatarioMapper.donatarioToListagemDto(eventoAtualizado);

        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> apagarPorId(@PathVariable Integer id) {
        if (!donatarioRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        donatarioRepository.deleteById(id);

        return ResponseEntity.ok(null);
    }
}
