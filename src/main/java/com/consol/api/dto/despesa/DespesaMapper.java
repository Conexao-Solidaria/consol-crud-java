package com.consol.api.dto.despesa;

import com.consol.api.entity.Despesa;
import com.consol.api.entity.Familia;

import java.util.List;

public class DespesaMapper {

    public static Despesa toEntity(DespesaCadastroDto dto) {
        Despesa despesa = new Despesa();
        despesa.setTipo(dto.getTipo());
        despesa.setGasto(dto.getGasto());

        return despesa;
    }

    public static Despesa toEntity(DespesaAtualizarDto dto){
        if (dto == null) return null;

        Despesa entity = new Despesa();
        entity.setGasto(dto.getGasto());
        entity.setTipo(dto.getTipo());

        return entity;

    }

    public static Despesa atualizacaoDtoToDespesa(DespesaAtualizarDto dto) {
        Despesa despesa = new Despesa();
        despesa.setTipo(dto.getTipo());
        despesa.setGasto(dto.getGasto());
        return despesa;
    }

    public static DespesaConsultaDto toDto(Despesa entity){
        DespesaConsultaDto dto = new DespesaConsultaDto();
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo());
        dto.setGasto(entity.getGasto());

        if (entity.getFamilia() != null){
            dto.setFamiliaDto(toDtoFamilia(entity.getFamilia()));
        }

        return dto;
    }


    public static DespesaConsultaDto.FamiliaDto toDtoFamilia(Familia entity){
        DespesaConsultaDto.FamiliaDto dto = new DespesaConsultaDto.FamiliaDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCep(entity.getCep());
        dto.setNumeroCasa(entity.getNumeroCasa());
        dto.setRenda(entity.getRenda());
        return dto;
    }

    public static List<DespesaConsultaDto> toDto (List<Despesa> despesas) {
        return despesas.stream().map(DespesaMapper::toDto).toList();

    }


    public static List<DespesaConsultaDto> listagemDtoToDespesa(List<Despesa> despesas) {
        return despesas.stream().map(DespesaMapper::toDto).toList();
    }
}
