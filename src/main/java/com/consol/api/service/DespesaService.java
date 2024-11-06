package com.consol.api.service;

import com.consol.api.entity.Despesa;
import com.consol.api.entity.Familia;
import com.consol.api.entity.exception.EntidadeNaoEncontradaException;
import com.consol.api.entity.exception.RequisicaoIncorretaException;
import com.consol.api.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final FamiliaService familiaService;

    public Despesa salvar(Despesa despesa, int idFamilia) {
        Familia familia = familiaService.porId(idFamilia);

        if (familia == null) throw new RequisicaoIncorretaException("Família");

        despesa.setFamilia(familia);
        Despesa despesaSalva = despesaRepository.save(despesa);
        return despesaSalva;
    }

    public Despesa buscarPorId(Integer id) {
        return despesaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Despesa")
        );
    }

    public List<Despesa> listarPorFamilia(int idFamilia) {
        familiaService.porId(idFamilia);
        return despesaRepository.findByFamiliaId(idFamilia);
    }

    public Despesa atualizarDespesa(Despesa despesa, Integer id){
        Optional <Despesa> despesaBanco = despesaRepository.findById(id);

        if (despesaBanco == null) throw new EntidadeNaoEncontradaException("Despesa");

        Despesa despesaAtualizar = despesaBanco.get();

        if (despesa.getTipo() != null && !despesa.getTipo().equals("") && !despesa.getTipo().equals(" ")) despesaAtualizar.setTipo(despesa.getTipo());
        if (despesa.getGasto() != null) despesaAtualizar.setGasto(despesa.getGasto());


        return despesaRepository.save(despesaAtualizar);

    }

    public void deletarPorId(Integer id) {
        Boolean exist = despesaRepository.existsById(id);

        if (!exist) throw new EntidadeNaoEncontradaException("Despesa");
        despesaRepository.deleteById(id);

    }

    public List<Despesa> listar(){
        return despesaRepository.findAll();
    }
}
