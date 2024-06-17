package com.consol.api.service;

import com.consol.api.dto.despesa.DespesaAtualizarDto;
import com.consol.api.dto.despesa.DespesaCadastroDto;
import com.consol.api.dto.despesa.DespesaConsultaDto;
import com.consol.api.dto.despesa.DespesaMapper;
import com.consol.api.entity.Despesa;
import com.consol.api.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DespesaService {

    @Autowired
    private final DespesaRepository despesaRepository;

    public List<DespesaConsultaDto> listar() {
        List<Despesa> despesas = despesaRepository.findAll();
        return DespesaMapper.listagemDtoToDespesa(despesas);
    }

    public DespesaConsultaDto salvar(DespesaCadastroDto despesaCadastroDto) {
        if (despesaCadastroDto == null) return null;

        Despesa despesaSalvar = DespesaMapper.cadastroDtoToDespesa(despesaCadastroDto);
        Despesa despesaSalva = despesaRepository.save(despesaSalvar);

        return DespesaMapper.despesaToListagemDto(despesaSalva);
    }

    public List<Despesa> buscarDespesasPorDonatario(String nomeDonatario){
        return null;
    }

    public List<Despesa> buscarDespesasPorFamilia(String nomeFamilia){
        return null;
    }

    public DespesaConsultaDto atualizarDespesa(Integer id, DespesaAtualizarDto despesaAtualizarDto){
        Optional<Despesa> despesaBuscadaOpt = despesaRepository.findById(id);
        if (despesaBuscadaOpt.isEmpty()) return null;

        Despesa despesaBuscada = despesaBuscadaOpt.get();
        Despesa despesa = DespesaMapper.atualizacaoDtoToDespesa(despesaAtualizarDto);
        despesa.setId(id);

        if (despesa.getTipo() == null) despesa.setTipo(despesaBuscada.getTipo());
        if (despesa.getGasto() == null) despesa.setGasto(despesaBuscada.getGasto());


        Despesa eventoAtualizado = despesaRepository.save(despesa);
        return DespesaMapper.despesaToListagemDto(eventoAtualizado);
    }

    public Boolean deletarDespesa(Integer id)   {
        if (!despesaRepository.existsById(id)) return false;
        despesaRepository.deleteById(id);
        return true;
    }

}
