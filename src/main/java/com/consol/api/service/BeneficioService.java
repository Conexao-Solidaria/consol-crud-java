package com.consol.api.service;

import com.consol.api.entity.Beneficio;
import com.consol.api.entity.exception.EntidadeNaoEncontradaException;
import com.consol.api.repository.BeneficioRepository;
import com.consol.api.repository.DonatarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeneficioService {

    private final BeneficioRepository beneficioRepository;
    private final DonatarioRepository donatarioRepository;

    public Beneficio salvar(Beneficio beneficio, int idDonatario) {
        if (donatarioRepository.findById(idDonatario).isEmpty()){
            throw new EntidadeNaoEncontradaException();
        }

        beneficio.setDonatario(donatarioRepository.findById(idDonatario).get());
        return beneficioRepository.save(beneficio);
    }

    public List<Beneficio> listar() {
        return beneficioRepository.findAll();
    }

    public Beneficio listarPorId(int id) {
        return beneficioRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException()
        );
    }

    public List<Beneficio> listarPorDonatario(String donatario) {
        return null;
    }

    public List<Beneficio> listarPorFamilia(String familia) {
        return null;
    }

    public Beneficio atualizar(int i, Beneficio beneficio) {
        return null;
    }

    public void deletar(int id) { }
}
