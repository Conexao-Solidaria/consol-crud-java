package com.consol.api.dto.usuario;

import com.consol.api.entity.Instituicao;
import com.consol.api.entity.Usuario;
import com.consol.api.repository.UsuarioRepository;

import java.util.List;

public class UsuarioMapper {
    public static UsuarioConsultaDto toDto(Usuario entity){
        if (entity == null) return null;

        UsuarioConsultaDto dto = new UsuarioConsultaDto();
        dto.setId(entity.getId());
        dto.setCoordenador(entity.getCoordenador());
        dto.setNomeUsuario(entity.getNomeUsuario());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());


        if (entity.getInstituicao() != null) dto.setInstituicao(toDtoInstituicao(entity.getInstituicao()));

        return dto;
    }



    private static UsuarioConsultaDto.Instituicao toDtoInstituicao(Instituicao instituicao){

        UsuarioConsultaDto.Instituicao instituicaoDto = new UsuarioConsultaDto.Instituicao();

        instituicaoDto.setId(instituicao.getId());
        instituicaoDto.setNome(instituicao.getNome());
        instituicaoDto.setCep(instituicao.getCep());
        instituicaoDto.setNumeroImovel(instituicao.getNumeroImovel());
        instituicaoDto.setDescricao(instituicao.getDescricao());

        return instituicaoDto;

    }

    public static Usuario toEntity(UsuarioCriacaoDto dto){
        Usuario entity = new Usuario();
        entity.setCoordenador(dto.getCoordenador());
        entity.setNomeUsuario(dto.getNomeUsuario());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setCpf(dto.getCpf());

        return entity;
    }


    public static Usuario toEntity(UsuarioAtualizacaoDto dto){
        Usuario entity = new Usuario();
        entity.setCoordenador(dto.getCoordenador());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());

        return entity;
    }


    public static List<UsuarioConsultaDto>  toDto(List<Usuario> entities){
        return entities.stream().map(UsuarioMapper::toDto).toList();
    }

}
