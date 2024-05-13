package com.example.APIcrudconsol.instituicao.dto;
import com.example.APIcrudconsol.instituicao.Instituicao;

import java.util.List;

public class InstituicaoMapper {

    // Converte uma entidade Instituicao para InstituicaoListagemDto
    public static InstituicaoConsultaDto instituicaoToListagemDto(Instituicao instituicao) {
        InstituicaoConsultaDto dto = new InstituicaoConsultaDto();
        dto.setIdInstituicao(instituicao.getIdInstituicao());
        dto.setNomeInstituicao(instituicao.getNomeInstituicao());
        dto.setCep(instituicao.getCep());
        dto.setNumeroImovel(instituicao.getNumeroImovel());
        dto.setDescricao(instituicao.getDescricao());
        dto.setFotoPerfil(instituicao.getFotoPerfil());
        return dto;
    }

    // Converte uma DTO InstituicaoCadastroDto para a entidade Instituicao
    public static Instituicao cadastroDtoToInstituicao(InstituicaoCadastroDto dto) {
        Instituicao instituicao = new Instituicao();
        instituicao.setNomeInstituicao(dto.getNomeInstituicao());
        instituicao.setCep(dto.getCep());
        instituicao.setNumeroImovel(dto.getNumeroImovel());
        instituicao.setDescricao(dto.getDescricao());
        instituicao.setFotoPerfil(dto.getFotoPerfil());
        return instituicao;
    }
    public static Instituicao atualizacaoDtoToInstituicao(InstituicaoAtualizarDto dto) {
        Instituicao instituicao = new Instituicao();
        instituicao.setNomeInstituicao(dto.getNomeInstituicao());
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

