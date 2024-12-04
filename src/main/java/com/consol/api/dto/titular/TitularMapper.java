package com.consol.api.dto.titular;

import com.consol.api.controller.TitularController;
import com.consol.api.entity.Familia;
import com.consol.api.entity.Titular;

import java.util.List;

public class TitularMapper {

    public static TitularConsultaDto toDto(Titular entity) {
        if (entity == null) return null;

        TitularConsultaDto dto = new TitularConsultaDto();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setRg(entity.getRg());
        dto.setCpf(entity.getCpf());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setTelefone1(entity.getTelefone1());
        dto.setTelefone2(entity.getTelefone2());
        dto.setEstadoCivil(entity.getEstadoCivil());
        dto.setEscolaridade(entity.getEscolaridade());
        dto.setTrabalhando(entity.getTrabalhando());
        dto.setOcupacao(entity.getOcupacao());

        if (entity.getFamilia() != null){
            dto.setFamilia(toDto(entity.getFamilia()));
        }


        dto.setReferenciaS3(entity.getReferenciaS3());
        return dto;
    }

    private static TitularConsultaDto.FamiliaDto toDto(Familia entity){
        TitularConsultaDto.FamiliaDto dto = new TitularConsultaDto.FamiliaDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCep(entity.getCep());
        dto.setCep(entity.getCep());
        dto.setNumeroCasa(entity.getNumeroCasa());
        dto.setRenda(entity.getRenda());
        dto.setFlagRetirada(entity.getFlagRetirada());
        dto.setDataCadastro(entity.getDataCadastro());

        return dto;
    }

    public static Titular toEntity(TitularCadastroDto dto) {
        if (dto == null) return null;

        Titular titular = new Titular();

        titular.setNome(dto.getNome());
        titular.setRg(dto.getRg());
        titular.setCpf(dto.getCpf());
        titular.setDataNascimento(dto.getDataNascimento());
        titular.setTelefone1(dto.getTelefone1());
        titular.setTelefone2(dto.getTelefone2());
        titular.setEstadoCivil(dto.getEstadoCivil());
        titular.setEscolaridade(dto.getEscolaridade());
        titular.setTrabalhando(dto.getTrabalhando());
        titular.setOcupacao(dto.getOcupacao());
        return titular;
    }

    public static Titular toEntity(TitularAtualizarDto dto) {
        if (dto == null) return null;

        Titular titular = new Titular();

        titular.setNome(dto.getNome());
        titular.setRg(dto.getRg());
        titular.setCpf(dto.getCpf());
        titular.setDataNascimento(dto.getDataNascimento());
        titular.setTelefone1(dto.getTelefone1());
        titular.setTelefone2(dto.getTelefone2());
        titular.setEstadoCivil(dto.getEstadoCivil());
        titular.setEscolaridade(dto.getEscolaridade());
        titular.setTrabalhando(dto.getTrabalhando());
        titular.setOcupacao(dto.getOcupacao());

        return titular;
    }

    public static List<TitularConsultaDto> toDto(List<Titular> titulars){
        return titulars.stream().map(TitularMapper::toDto).toList();
    }

}
