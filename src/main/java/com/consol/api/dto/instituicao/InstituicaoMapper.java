package com.consol.api.dto.instituicao;

import com.consol.api.entity.Instituicao;

import java.util.List;

public class InstituicaoMapper {

    // Converte uma entidade Instituicao para InstituicaoListagemDto
    public static InstituicaoConsultaDto instituicaoToListagemDto(Instituicao instituicao) {
        InstituicaoConsultaDto dto = new InstituicaoConsultaDto();
        dto.setId(instituicao.getId());
        dto.setNome(instituicao.getNome());
        dto.setCep(instituicao.getCep());
        dto.setNumeroImovel(instituicao.getNumeroImovel());
        dto.setDescricao(instituicao.getDescricao());
        dto.setFotoPerfil(instituicao.getFotoPerfil());
        return dto;
    }

    // Converte uma DTO InstituicaoCadastroDto para a entidade Instituicao
    public static Instituicao cadastroDtoToInstituicao(InstituicaoCadastroDto dto) {
        Instituicao instituicao = new Instituicao();
        instituicao.setNome(dto.getNome());
        instituicao.setCep(dto.getCep());
        instituicao.setNumeroImovel(dto.getNumeroImovel());
        instituicao.setDescricao(dto.getDescricao());
        instituicao.setFotoPerfil(dto.getFotoPerfil());
        return instituicao;
    }
    public static Instituicao atualizacaoDtoToInstituicao(InstituicaoAtualizarDto dto) {
        Instituicao instituicao = new Instituicao();
        instituicao.setNome(dto.getNome());
        instituicao.setCep(dto.getCep());
        instituicao.setNumeroImovel(dto.getNumeroImovel());
        instituicao.setDescricao(dto.getDescricao());
        instituicao.setFotoPerfil(dto.getFotoPerfil());
        return instituicao;
    }

    public static List<InstituicaoConsultaDto> listagemDtoToInstituicaoLista(List<Instituicao> instituicaos){
        return instituicaos.stream().map(InstituicaoMapper::instituicaoToListagemDto).toList();
    }
}

