package com.consol.api.service;

import com.consol.api.dto.instituicao.InstituicaoMapper;
import com.consol.api.entity.Instituicao;
import com.consol.api.entity.Necessidade;
import com.consol.api.repository.InstituicaoRepository;
import com.consol.api.repository.NecessidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NecessidadeService {
    private final NecessidadeRepository repository;
    private final InstituicaoService service;
    private final InstituicaoRepository instituicaoRepository;

    public Necessidade cadastrar(Necessidade necessidade) {
        Optional<Instituicao> instituicao = instituicaoRepository.findById(necessidade.getId());

        if(instituicao.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        necessidade.setId(0);

        necessidade.setInstituicao(instituicao.get());
        repository.save(necessidade);

        return necessidade;
    }

    public Necessidade buscarPorId(int id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<Necessidade> buscarPorTipo(String tipo) {
        return repository.findByTipoContainsIgnoreCase(tipo);
    }

    public Necessidade atualizar(int id, Necessidade necessidade) {
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        necessidade.setId(id);
        return repository.save(necessidade);
    }

    public void deletar(int id){
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        repository.deleteById(id);
    }

    public List<Necessidade> buscarTodos(){
        return repository.findAll();
    }
}
