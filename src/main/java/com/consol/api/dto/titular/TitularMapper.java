package com.consol.api.dto.titular;

import com.consol.api.entity.Titular;

import java.util.List;

public class TitularMapper {

    public static TitularConsultaDto toDto(Titular titular) {
        if (titular == null) return null;

        TitularConsultaDto dto = new TitularConsultaDto();

        dto.setId(titular.getId());
        dto.setDataCadastro(titular.getDataCadastro());
        dto.setNome(titular.getNome());
        dto.setRg(titular.getRg());
        dto.setCpf(titular.getCpf());
        dto.setDataNascimento(titular.getDataNascimento());
        dto.setTelefone1(titular.getTelefone1());
        dto.setTelefone2(titular.getTelefone2());
        dto.setEstadoCivil(titular.getEstadoCivil());
        dto.setEscolaridade(titular.getEscolaridade());
        dto.setTrabalhando(titular.getTrabalhando());
        dto.setOcupacao(titular.getOcupacao());

        return dto;
    }

    public static Titular toEntity(TitularCadastroDto dto) {
        if (dto == null) return null;

        Titular titular = new Titular();

        titular.setDataCadastro(dto.getDataCadastro());
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
