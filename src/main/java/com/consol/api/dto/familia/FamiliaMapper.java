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
        dto.setDataCadastro(familia.getDataCadastro());

        dto.setTitulares(toDonatarioDto(familia.getTitulares()));

        dto.setDespesas(toDespesaDto(familia.getDespesas()));

        return dto;
    }

    public static List<FamiliaConsultaDto.TitularDto> toDonatarioDto(List<Titular> entities) {
        if (entities == null) return null;

        List<FamiliaConsultaDto.TitularDto> titularDtos = new ArrayList<>();

        for (Titular entityAtual : entities) {
            FamiliaConsultaDto.TitularDto dto = new FamiliaConsultaDto.TitularDto();
            dto.setId(entityAtual.getId());
            dto.setNome(entityAtual.getNome());
            dto.setRg(entityAtual.getRg());
            dto.setCpf(entityAtual.getCpf());
            dto.setDataNascimento(entityAtual.getDataNascimento());
            dto.setTelefone1(entityAtual.getTelefone1());
            dto.setTelefone2(entityAtual.getTelefone2());
            dto.setEstadoCivil(entityAtual.getEstadoCivil());
            dto.setEscolaridade(entityAtual.getEscolaridade());
            dto.setTrabalho(entityAtual.getTrabalhando());
            dto.setOcupacao(entityAtual.getOcupacao());

            titularDtos.add(dto);
        }
        return titularDtos;
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
        familia.setFlagRetirada((byte) 0);
        familia.setDataCadastro(dto.getDataCadastro());

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
