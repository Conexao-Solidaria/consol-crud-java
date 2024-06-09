package com.consol.api.service;

import com.consol.api.entity.Beneficio;
import com.consol.api.entity.Donatario;
import com.consol.api.entity.exception.EntidadeNaoEncontradaException;
import com.consol.api.repository.BeneficioRepository;
import com.consol.api.repository.DonatarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeneficioService {

    private final BeneficioRepository beneficioRepository;
    private final DonatarioRepository donatarioRepository;
    private final FamiliaService familiaService;

    public Beneficio salvar(Beneficio beneficio, int idDonatario) {
        Optional<Donatario> donatario = donatarioRepository.findById(idDonatario);

        if (donatario.isEmpty()){
            throw new EntidadeNaoEncontradaException();
        }

        beneficio.setDonatario(donatario.get());
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

    public List<Beneficio> listarPorDonatario(int idDonatario) {
        if (!donatarioRepository.existsById(idDonatario)) throw new EntidadeNaoEncontradaException();
        return beneficioRepository.findByDonatario_id(idDonatario);

    }

    public List<Beneficio> listarPorFamilia(int idFamilia) {
       familiaService.porId(idFamilia);
        return beneficioRepository.findByDonatario_Familia_id(idFamilia);
    }


    public Beneficio atualizar(int idBenefico, Beneficio beneficio) {
        return null;
    }

    public void deletar(int id) { }
}
