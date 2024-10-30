package com.consol.api.service;

import com.consol.api.entity.Familia;
import com.consol.api.entity.exception.EntidadeNaoEncontradaException;
import com.consol.api.entity.exception.RequisicaoIncorretaException;
import com.consol.api.repository.FamiliaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.bytecode.internal.bytebuddy.BytecodeProviderImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FamiliaService {

    private final FamiliaRepository repository;

    public List<Familia> listar(){
        return repository.findAll();
    }

    public Familia porId(int id) {
        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Familia")
        );
    }

    public Familia atualizar(int id, Familia familia) {
        Familia familiaAtualizada = porId(id);

        familiaAtualizada.setCep(familia.getCep());
        familiaAtualizada.setNumeroCasa(familia.getNumeroCasa());
        return repository.save(familiaAtualizada);
    }

    public Familia salvar(Familia familia) {
        return repository.save(familia);
    }

    public void deletar(int id) {
        if (!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }

    public Familia atualizarFlag(int id, Familia familia){
        Familia familiaAtualizar = porId(id);

        if (familia.getFlagRetirada() != (byte) 0 && familia.getFlagRetirada() != 1) throw new RequisicaoIncorretaException("Atualizar família");

        familiaAtualizar.setFlagRetirada(familia.getFlagRetirada());
        return repository.save(familiaAtualizar);
    }

    public Boolean familiaExiste(int id){
        return repository.existsById(id);
    }

    public Integer qtdFamilia(){
        return repository.countBy();
    }

    public Integer proximosVencimento(LocalDate data){
        LocalDate dataBase = data.minusMonths(2);
        return repository.countByDataCadastroBefore(dataBase);
    }

}
