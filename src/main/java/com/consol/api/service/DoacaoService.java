package com.consol.api.service;

import com.consol.api.entity.Doacao;
import com.consol.api.repository.DoacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoacaoService {

    private final DoacaoRepository repository;

    public Doacao salvar(Doacao doacao) {
        return repository.save(doacao);
    }

    public List<Doacao> listar() {
        return repository.findAll();
    }

    public Doacao listarPorId(int id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<Doacao> listarPorData(LocalDate dataDoacao) {
        return repository.findByDataDoacao(dataDoacao);
    }

    public List<Doacao> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return repository.findByDataDoacaoBetween(dataInicio, dataFim);
    }
}
