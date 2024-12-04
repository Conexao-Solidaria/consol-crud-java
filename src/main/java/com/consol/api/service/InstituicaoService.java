package com.consol.api.service;

import com.consol.api.entity.Instituicao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.consol.api.repository.InstituicaoRepository;

@Service
@RequiredArgsConstructor
public class InstituicaoService {
//
        private final InstituicaoRepository repository;
//
//        public Instituicao criar(Instituicao instituicao) {
//            return repository.save(instituicao);
//        }
//
//        public List<Instituicao> listarInstituicoes() {
//            return repository.findAll();
//        }
//
        public Instituicao consultarPorId(Integer id) {
            return repository.findById(id).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
        }
//
//        public Instituicao atualizar(Integer id, Instituicao instituicao) {
//            Instituicao instituicaoAtualizada = consultarPorId(id);
//
//            instituicaoAtualizada.setNome(instituicao.getNome());
//            instituicaoAtualizada.setCep(instituicao.getCep());
//            instituicaoAtualizada.setNumeroImovel(instituicao.getNumeroImovel());
//            instituicaoAtualizada.setDescricao(instituicao.getDescricao());
//            instituicaoAtualizada.setFotoPerfil(instituicao.getFotoPerfil());
//
//            return repository.save(instituicaoAtualizada);
//        }
//
//        public void apagarPorId(Integer id) {
//            if (!repository.existsById(id)){
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//            }
//            repository.deleteById(id);
//        }
//
//        public boolean existById(int id){
//            return repository.existsById(id);
//        }
//
    }
//
