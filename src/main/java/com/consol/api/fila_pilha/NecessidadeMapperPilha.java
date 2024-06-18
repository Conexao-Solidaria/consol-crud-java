package com.consol.api.fila_pilha;

import com.consol.api.dto.necessidade.NecessidadeCriacaoDto;
import com.consol.api.entity.Necessidade;

public class NecessidadeMapperPilha {
    public static NecessidadeCriacaoDto toDto(Necessidade entity, int idInstituicao){
        NecessidadeCriacaoDto dto = new NecessidadeCriacaoDto();

        dto.setTipo(entity.getTipo());
        dto.setDescricao(entity.getDescricao());
        dto.setInstituicao_id(idInstituicao);

        return dto;
    }
}
