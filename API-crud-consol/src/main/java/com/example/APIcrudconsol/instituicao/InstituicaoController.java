package com.example.APIcrudconsol.instituicao;

import com.example.APIcrudconsol.instituicao.dto.InstituicaoAtualizarDto;
import com.example.APIcrudconsol.instituicao.dto.InstituicaoCadastroDto;
import com.example.APIcrudconsol.instituicao.dto.InstituicaoConsultaDto;
import com.example.APIcrudconsol.instituicao.dto.InstituicaoMapper;
import com.example.APIcrudconsol.instituicao.service.InstituicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoController {

    @Autowired
    private InstituicaoService instituicaoService;

    @PostMapping
    public ResponseEntity<InstituicaoConsultaDto> criar(@RequestBody @Valid InstituicaoCadastroDto instituicaoCadastroDto) {
        InstituicaoConsultaDto instituicaoConsultaDto = instituicaoService.criar(instituicaoCadastroDto);
        return ResponseEntity.status(instituicaoConsultaDto != null ? 201 : 400).body(instituicaoConsultaDto);
    }

    @GetMapping
    public ResponseEntity<List<InstituicaoConsultaDto>> listagemInstituicao() {
        List<InstituicaoConsultaDto> instituicoes = instituicaoService.listarInstituicoes();
        return ResponseEntity.status(instituicoes.isEmpty() ? 204 : 200).body(instituicoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoConsultaDto> consultarPorId(@PathVariable Integer id) {
        InstituicaoConsultaDto instituicaoConsultaDto = instituicaoService.consultarPorId(id);
        return ResponseEntity.status(instituicaoConsultaDto != null ? 200 : 404).body(instituicaoConsultaDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<InstituicaoConsultaDto> atualizar(@RequestBody @Valid InstituicaoAtualizarDto instituicaoAtualizarDto, @PathVariable Integer id) {
        InstituicaoConsultaDto instituicaoConsultaDto = instituicaoService.atualizar(instituicaoAtualizarDto, id);
        return ResponseEntity.status(instituicaoConsultaDto != null ? 200 : 404).body(instituicaoConsultaDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> apagarPorId(@PathVariable Integer id) {
        boolean sucesso = instituicaoService.apagarPorId(id);
        return ResponseEntity.status(sucesso ? 200 : 404).build();
    }
}