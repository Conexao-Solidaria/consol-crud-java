package com.consol.api.dto.beneficio;

import com.consol.api.entity.Beneficio;

import java.util.List;

public class BeneficioMapper {
    public static Beneficio toEntity(BeneficoCriacaoDto dto){
        if (dto == null) return null;

        Beneficio entity = new Beneficio();
        entity.setNome(dto.getNome());
        entity.setValor(dto.getValor());

        return entity;
    }

    public static BeneficioConsultaDto toDto(Beneficio entity){
        BeneficioConsultaDto dto = new BeneficioConsultaDto();
        dto.setId(entity.getIdBeneficio());
        dto.setNome(entity.getNome());
        dto.setValor(entity.getValor());

        return dto;
    }

    public static List<BeneficioConsultaDto> toDto(List<Beneficio> entities){
        return entities.stream().map(BeneficioMapper::toDto).toList();
    }

}
