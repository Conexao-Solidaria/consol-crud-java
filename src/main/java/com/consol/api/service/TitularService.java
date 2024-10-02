package com.consol.api.service;

import com.consol.api.entity.Titular;
import com.consol.api.entity.Familia;
import com.consol.api.repository.TitularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitularService {

    private final TitularRepository repository;

    private final FamiliaService familiaService;

    public List<Titular> listar() {
        return repository.findAll();
    }

    public Titular salvar(Titular titular, Integer idFamilia) {
        Familia familia = familiaService.porId(idFamilia);

        titular.setFamilia(familia);
        return repository.save(titular);
    }

    public Titular porId(int id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<Titular> listarPorNome(String nome) {
        return repository.findByNomeContainsIgnoreCase(nome);
    }

    public Titular atualizar(int id, Titular titular) {
        Titular titularAtualizado = porId(id);

        titularAtualizado.setNome(titular.getNome());
        titularAtualizado.setSobrenome(titular.getSobrenome());
        titularAtualizado.setEstadoCivil(titular.getEstadoCivil());
        titularAtualizado.setEscolaridade(titular.getEscolaridade());
        titularAtualizado.setTelefone1(titular.getTelefone1());
        titularAtualizado.setTelefone2(titular.getTelefone2());
        titularAtualizado.setTrabalhando(titular.getTrabalhando());
        titularAtualizado.setOcupacao(titular.getOcupacao());

        return repository.save(titularAtualizado);
    }

    public void deletar(int id) {
        if (!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }

    public Boolean existById(int id){
        return repository.existsById(id);
    }
}
