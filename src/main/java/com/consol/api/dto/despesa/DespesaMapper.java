package com.consol.api.dto.despesa;

import com.consol.api.entity.Despesa;
import com.consol.api.entity.Familia;

import java.util.List;

public class DespesaMapper {

    public static DespesaConsultaDto despesaToListagemDto(Despesa despesa) {

        DespesaConsultaDto dto = new DespesaConsultaDto();
        dto.setId(despesa.getId());
        dto.setTipo(despesa.getTipo());
        dto.setGasto(despesa.getGasto());
        return dto;
    }

    public static List<DespesaConsultaDto> despesaToListagemDto (List<Despesa> despesas) {
        return despesas.stream().map(DespesaMapper::despesaToListagemDto).toList();

    }

    public static Despesa cadastroDtoToDespesa(DespesaCadastroDto dto) {
        Despesa despesa = new Despesa();
        despesa.setTipo(dto.getTipo());
        despesa.setGasto(dto.getGasto());

        Familia familia = Familia.builder()
                        .id(dto.getFamiliaDto().getId())
                        .nome(dto.getFamiliaDto().getNome())
                        .cep(dto.getFamiliaDto().getCep())
                        .numeroCasa(dto.getFamiliaDto().getNumeroCasa())
                        .renda(dto.getFamiliaDto().getRenda()).build();

        despesa.setFamilia(familia);
        return despesa;
    }

    public static Despesa atualizacaoDtoToDespesa(DespesaAtualizarDto dto) {
        Despesa despesa = new Despesa();
        despesa.setTipo(dto.getTipo());
        despesa.setGasto(dto.getGasto());
        return despesa;
    }

    public static List<DespesaConsultaDto> listagemDtoToDespesa(List<Despesa> despesas) {
        return despesas.stream().map(DespesaMapper::despesaToListagemDto).toList();
    }
}
