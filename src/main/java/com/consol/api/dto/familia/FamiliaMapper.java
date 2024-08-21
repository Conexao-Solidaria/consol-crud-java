package com.consol.api.dto.familia;

import com.consol.api.entity.Despesa;
import com.consol.api.entity.Titular;
import com.consol.api.entity.Familia;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FamiliaMapper {


    public static FamiliaConsultaDto toDto(Familia familia) {
        if (familia == null) return null;

        FamiliaConsultaDto dto = new FamiliaConsultaDto();

        dto.setId(familia.getId());
        dto.setNome(familia.getNome());
        dto.setCep(familia.getCep());
        dto.setNumeroCasa(familia.getNumeroCasa());
        dto.setRenda(familia.getRenda());
        dto.setFlagRetirada(familia.getFlagRetirada());

        dto.setDonatarios(toDonatarioDto(familia.getTitulars()));

        dto.setDespesas(toDespesaDto(familia.getDespesas()));

        return dto;
    }

    public static List<FamiliaConsultaDto.DonatarioDto> toDonatarioDto(List<Titular> titulars) {
        if (titulars == null) return null;

        List<FamiliaConsultaDto.DonatarioDto> donatarioDtoList = new ArrayList<>();

        for (Titular titularAtual : titulars) {
            FamiliaConsultaDto.DonatarioDto donatarioDto = new FamiliaConsultaDto.DonatarioDto();
            donatarioDto.setId(titularAtual.getId());
            donatarioDto.setEstadoCivil(titularAtual.getEstadoCivil());
            donatarioDto.setEscolaridade(titularAtual.getEscolaridade());
            donatarioDto.setTrabalhando(titularAtual.getTrabalhando());
            donatarioDto.setOcupacao(titularAtual.getOcupacao());

            donatarioDtoList.add(donatarioDto);
        }
        return donatarioDtoList;
    }

    public static List<FamiliaConsultaDto.DespesaDto> toDespesaDto(List<Despesa> despesas) {
        if (despesas == null) return null;

        List<FamiliaConsultaDto.DespesaDto> despesaDtoList = new ArrayList<>();

        for (Despesa despesaAtual : despesas) {
            FamiliaConsultaDto.DespesaDto despesaDto = new FamiliaConsultaDto.DespesaDto();
            despesaDto.setId(despesaAtual.getId());
            despesaDto.setTipo(despesaAtual.getTipo());
            despesaDto.setGasto(despesaAtual.getGasto());

            despesaDtoList.add(despesaDto);
        }
        return despesaDtoList;
    }

    public static Familia toEntity(FamiliaCadastroDto dto) {
        if (dto == null) return null;

        Familia familia = new Familia();
        familia.setNome(dto.getNome());
        familia.setCep(dto.getCep());
        familia.setNumeroCasa(dto.getNumeroCasa());
        familia.setRenda(dto.getRenda());
        familia.setFlagRetirada(dto.getFlagRetirada());

        return familia;
    }

    public static Familia toEntity(FamiliaAtualizarDto dto) {
        if (dto == null) return null;

        Familia familia = new Familia();
        familia.setCep(dto.getCep());
        familia.setNumeroCasa(dto.getNumeroCasa());

        return familia;
    }

    public static Familia toEntity(FamiliaAtualizarFlagDto dto){
        if (dto == null) return null;

        Familia familia = new Familia();
        familia.setFlagRetirada(dto.getFlagRetirada());

        return familia;
    }

    public static List<FamiliaConsultaDto> toDto(List<Familia> familias) {
        return familias.stream().map(FamiliaMapper::toDto).toList();
    }
}
