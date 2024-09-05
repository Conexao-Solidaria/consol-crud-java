package com.consol.api.service;

import com.consol.api.entity.Beneficio;
import com.consol.api.entity.Titular;
import com.consol.api.entity.exception.EntidadeNaoEncontradaException;
import com.consol.api.repository.BeneficioRepository;
import com.consol.api.repository.TitularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeneficioService {

    private final BeneficioRepository beneficioRepository;
    private final TitularRepository titularRepository;
    private final FamiliaService familiaService;

    public Beneficio salvar(Beneficio beneficio, int idDonatario) {
        Optional<Titular> donatario = titularRepository.findById(idDonatario);

        if (donatario.isEmpty()){
            throw new EntidadeNaoEncontradaException("Benefício");
        }

        beneficio.setTitular(donatario.get());
        return beneficioRepository.save(beneficio);
    }

    public List<Beneficio> listar() {
        return beneficioRepository.findAll();
    }

    public Beneficio listarPorId(int id) {
        return beneficioRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Benefício")
        );
    }

    public List<Beneficio> listarPorDonatario(int idDonatario) {
        if (!titularRepository.existsById(idDonatario)) throw new EntidadeNaoEncontradaException("Titular");
        return beneficioRepository.findByTitularId(idDonatario);

    }

    public List<Beneficio> listarPorFamilia(int idFamilia) {
       familiaService.porId(idFamilia);
        return beneficioRepository.findByTitularFamiliaId(idFamilia);
    }


    public Beneficio atualizar(int idBenefico, Beneficio beneficioAtualizado) {
        Optional<Beneficio> beneficio = beneficioRepository.findById(idBenefico);
        if (beneficio.isEmpty()) throw new EntidadeNaoEncontradaException("Beneficio");

        beneficioAtualizado.setId(beneficio.get().getId());
        beneficioAtualizado.setTitular(beneficio.get().getTitular());

        return beneficioRepository.save(beneficioAtualizado);
    }

    public void deletar(int idBenefico) {
        if (!beneficioRepository.existsById(idBenefico)) throw new EntidadeNaoEncontradaException("Beneficio");
        beneficioRepository.deleteById(idBenefico);
    }
}
