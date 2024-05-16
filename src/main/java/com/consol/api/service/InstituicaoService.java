package com.consol.api.service;

import com.consol.api.dto.instituicao.InstituicaoAtualizarDto;
import com.consol.api.dto.instituicao.InstituicaoCadastroDto;
import com.consol.api.dto.instituicao.InstituicaoConsultaDto;
import com.consol.api.dto.instituicao.InstituicaoMapper;
import com.consol.api.repository.InstituicaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstituicaoService {

        @Autowired
        private final InstituicaoRepository instituicaoRepository;

        public InstituicaoConsultaDto criar(InstituicaoCadastroDto instituicaoCadastroDto) {
            if (instituicaoCadastroDto == null) return null;

            Instituicao instituicaoSalvar = InstituicaoMapper.cadastroDtoToInstituicao(instituicaoCadastroDto);
            Instituicao instituicaoSalvo = instituicaoRepository.save(instituicaoSalvar);

            return InstituicaoMapper.instituicaoToListagemDto(instituicaoSalvo);
        }

        public List<InstituicaoConsultaDto> listarInstituicoes() {
            List<Instituicao> instituicaos = instituicaoRepository.findAll();
            return InstituicaoMapper.listagemDtoToInstituicaoLista(instituicaos);
        }

        public InstituicaoConsultaDto consultarPorId(Integer id) {
            Optional<Instituicao> instituicaoBuscado = instituicaoRepository.findById(id);
            return instituicaoBuscado.map(InstituicaoMapper::instituicaoToListagemDto).orElse(null);
        }

        public InstituicaoConsultaDto atualizar(InstituicaoAtualizarDto instituicaoAtualizarDto, Integer id) {
            Optional<Instituicao> instituicaoBuscadoOpt = instituicaoRepository.findById(id);
            if (instituicaoBuscadoOpt.isEmpty()) return null;

            Instituicao instituicaoBuscado = instituicaoBuscadoOpt.get();
            Instituicao instituicao = InstituicaoMapper.atualizacaoDtoToInstituicao(instituicaoAtualizarDto);
            instituicao.setIdInstituicao(id);

            if (instituicao.getCep() == null) instituicao.setCep(instituicaoBuscado.getCep());
            if (instituicao.getNumeroImovel() == null) instituicao.setNumeroImovel(instituicaoBuscado.getNumeroImovel());
            if (instituicao.getDescricao() == null) instituicao.setDescricao(instituicaoBuscado.getDescricao());
            if (instituicao.getFotoPerfil() == null) instituicao.setFotoPerfil(instituicaoBuscado.getFotoPerfil());

            Instituicao eventoAtualizado = instituicaoRepository.save(instituicao);
            return InstituicaoMapper.instituicaoToListagemDto(eventoAtualizado);
        }

        public boolean apagarPorId(Integer id) {
            if (!instituicaoRepository.existsById(id)) return false;
            instituicaoRepository.deleteById(id);
            return true;
        }
    }

