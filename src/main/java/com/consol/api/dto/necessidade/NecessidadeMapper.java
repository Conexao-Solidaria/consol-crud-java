package com.consol.api.dto.necessidade;

import com.consol.api.dto.instituicao.InstituicaoConsultaDto;
import com.consol.api.dto.instituicao.InstituicaoMapper;
import com.consol.api.entity.Instituicao;
import com.consol.api.entity.Necessidade;

import java.util.List;

public class NecessidadeMapper {
    public static NecessidadeListagemDto toDto(Necessidade entity){
        NecessidadeListagemDto dto = new NecessidadeListagemDto();
        NecessidadeListagemDto.InstituicaoDtoNecessidade dtoNecessidade = new NecessidadeListagemDto.InstituicaoDtoNecessidade();

        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo());
        dto.setDescricao(entity.getDescricao());

        dtoNecessidade.setCep(entity.getInstituicao().getCep());
        dtoNecessidade.setNome(entity.getInstituicao().getNome());
        dtoNecessidade.setDescricao(entity.getInstituicao().getDescricao());
        dtoNecessidade.setNumeroImovel(entity.getInstituicao().getNumeroImovel());
        dtoNecessidade.setId(entity.getInstituicao().getId());
        dtoNecessidade.setFotoPerfil(entity.getInstituicao().getFotoPerfil());

        dto.setInstituicao(dtoNecessidade);

        return dto;
    }

    public static Necessidade toEntity(NecessidadeCriacaoDto dto){
        Necessidade entity = new Necessidade();

        entity.setDescricao(dto.getDescricao());
        entity.setTipo(dto.getTipo());

        return entity;
    }

    public static Necessidade toEntity(NecessidadeAtualizarDto dto){
        Necessidade entity = new Necessidade();

        entity.setDescricao(dto.getDescricao());
        entity.setTipo(dto.getTipo());

        return entity;
    }


    public static List<NecessidadeListagemDto> toDtos(List<Necessidade> necessidades){
        return necessidades.stream().map(NecessidadeMapper::toDto).toList();
    }
}
