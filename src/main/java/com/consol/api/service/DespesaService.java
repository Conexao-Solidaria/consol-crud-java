package com.consol.api.service;

import com.consol.api.dto.despesa.DespesaAtualizarDto;
import com.consol.api.dto.despesa.DespesaCadastroDto;
import com.consol.api.dto.despesa.DespesaConsultaDto;
import com.consol.api.dto.despesa.DespesaMapper;
import com.consol.api.entity.Despesa;
import com.consol.api.repository.DespesaRepository;
import com.consol.api.repository.DonatarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DespesaService {

    @Autowired
    private final DespesaRepository despesaRepository;

    @Autowired
    private final DonatarioRepository donatarioRepository;

    private final FamiliaService familiaService;

    public List<Despesa> listar() {
        return despesaRepository.findAll();
    }

    public Despesa listarPorId(Integer id) {

        return despesaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public Despesa salvar(Despesa despesa) {
        if (despesa == null) return null;

        Despesa despesaSalva = despesaRepository.save(despesa);

        return despesaSalva;
    }

    public List<Despesa> listarPorDonatario(int idDonatario) {
        if (!donatarioRepository.existsById(idDonatario));
        return donatarioRepository.findByDonatario_id(idDonatario);

    }

    public List<Despesa> listarPorFamilia(int idFamilia) {
        familiaService.porId(idFamilia);
        return despesaRepository.findByFamiliaId(idFamilia);
    }

    public Despesa atualizarDespesa(Integer id, Despesa despesa){
        Optional<Despesa> despesaBuscadaOpt = despesaRepository.findById(id);
        if (despesaBuscadaOpt.isEmpty()) return null;

        Despesa despesaBuscada = despesaBuscadaOpt.get();
        despesa.setId(id);

        if (despesa.getTipo() == null) despesa.setTipo(despesaBuscada.getTipo());
        if (despesa.getGasto() == null) despesa.setGasto(despesaBuscada.getGasto());


        return despesaRepository.save(despesa);
    }

    public void deletarPorId(Integer id) {
        if (!despesaRepository.existsById(id));
        despesaRepository.deleteById(id);
    }
}
