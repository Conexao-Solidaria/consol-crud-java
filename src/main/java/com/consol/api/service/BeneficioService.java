package com.consol.api.service;

import com.consol.api.entity.Beneficio;
import com.consol.api.repository.BeneficioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeneficioService {

    private final BeneficioRepository beneficioRepository;

    public Beneficio salvar(Beneficio beneficio) {
        return null;
    }

    public List<Beneficio> listar() {
        return beneficioRepository.findAll();
    }

    public Beneficio listarPorId(int id) {
        return null;
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
