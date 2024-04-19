package com.example.APIcrudconsol.instituicao;

import com.example.APIcrudconsol.instituicao.dto.InstituicaoAtualizarDto;
import com.example.APIcrudconsol.instituicao.dto.InstituicaoCadastroDto;
import com.example.APIcrudconsol.instituicao.dto.InstituicaoConsultaDto;
import com.example.APIcrudconsol.instituicao.dto.InstituicaoMapper;
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
    InstituicaoRepository repositoryInst;

    @PostMapping
    public ResponseEntity<InstituicaoConsultaDto> criacaoProduto(@RequestBody @Valid InstituicaoCadastroDto instituicaoCadastroDto){
        if(instituicaoCadastroDto == null) return ResponseEntity.status(400).build();

        Instituicao instituicaoSalvar = InstituicaoMapper.cadastroDtoToInstituicao(instituicaoCadastroDto);

        Instituicao instituicaoSalvo = repositoryInst.save(instituicaoSalvar);

        InstituicaoConsultaDto instituicaoConsultaDto = InstituicaoMapper.instituicaoToListagemDto(instituicaoSalvo);

        return ResponseEntity.status(201).body(instituicaoConsultaDto);
    }

    @GetMapping
    public ResponseEntity<List<InstituicaoConsultaDto>> listagemInstituicao(){
        List<Instituicao> instituicaos = repositoryInst.findAll();

        if(instituicaos.isEmpty()) return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(InstituicaoMapper.listagemDtoToInstituicaoLista(instituicaos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoConsultaDto> consultarPorId(@PathVariable Integer id){
        Optional<Instituicao> instituicaoBuscado = repositoryInst.findById(id);

        if(instituicaoBuscado.isEmpty()) return ResponseEntity.status(404).build();

        InstituicaoConsultaDto dto = InstituicaoMapper.instituicaoToListagemDto(instituicaoBuscado.get());

        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<InstituicaoConsultaDto> atualizarEvento(@RequestBody @Valid InstituicaoAtualizarDto instituicaoAtualizarDto, @PathVariable Integer id){
        Optional<Instituicao> instituicaoBuscadoOpt = repositoryInst.findById(id);

        //Isso é idiota, o java por algum motivo quando atualiza o banco muda a variavel, esse foi o metodo que achei

        if(instituicaoBuscadoOpt.isEmpty()) return ResponseEntity.status(404).build();

        Instituicao instituicaoBuscado = instituicaoBuscadoOpt.get();

        Instituicao instituicao = InstituicaoMapper.atualizacaoDtoToInstituicao(instituicaoAtualizarDto);

        instituicao.setIdInstituicao(id);
        if(instituicao.getCep() == null) instituicao.setCep(instituicaoBuscado.getCep()); ;
        if(instituicao.getNumeroImovel() == null) instituicao.setNumeroImovel(instituicaoBuscado.getNumeroImovel()); ;
        if(instituicao.getDescricao() == null) instituicao.setDescricao(instituicaoBuscado.getDescricao());
        if(instituicao.getFotoPerfil() == null) instituicao.setFotoPerfil(instituicaoBuscado.getFotoPerfil());


        Instituicao eventoAtualizado = repositoryInst.save(instituicao);

        InstituicaoConsultaDto dto = InstituicaoMapper.instituicaoToListagemDto(eventoAtualizado);

        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id){
        if(!repositoryInst.existsById(id)){
            return ResponseEntity.status(404).build();
        }

        repositoryInst.deleteById(id);

        return ResponseEntity.ok(null);
    }
}