package com.consol.api.dto.usuario;

import com.consol.api.entity.Usuario;
import java.util.List;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioCadastroDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCpf(dto.getCpf());
        usuario.setCoordenador(dto.getCoordenador());
        usuario.setFlagAprovado(dto.getFlagAprovado());

        return usuario;
    }

    public static UsuarioConsultaDto toDto(Usuario usuario) {
        UsuarioConsultaDto dto = new UsuarioConsultaDto();
        dto.setIdUsuario(usuario.getId());
        dto.setNomeUsuario(usuario.getNomeUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setCpf(usuario.getCpf());
        dto.setCoordenador(usuario.getCoordenador());
        dto.setFlagAprovado(usuario.getFlagAprovado());

        if (usuario.getInstituicao() != null) {
            dto.setFkInstituicao(usuario.getInstituicao().getId());
        }

        return dto;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNomeUsuario());
        usuarioTokenDto.setCoordenador(usuario.getCoordenador());
        usuarioTokenDto.setFlagAprovado(usuario.getFlagAprovado());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }

    public static List<UsuarioConsultaDto> toDto(List<Usuario> usuarios){
        return usuarios.stream().map(UsuarioMapper::toDto).toList();
    }

    public static Usuario toEntity(UsuarioAtualizarFlagDto dto){
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setFlagAprovado(dto.getFlagAprovado());

        return usuario;
    }

    public static Usuario toEntity(UsuarioAtualizarCoordenadorDto dto){
        if (dto == null) return null;

        Usuario entity = new Usuario();
        entity.setCoordenador(dto.getCoordenador());

        return entity;
    }

    public static Usuario toEntity(UsuarioAtualizarFlagCoordenadorDto dto){
        if (dto == null) return null;

        Usuario entity = new Usuario();
        entity.setCoordenador(dto.getCoordenador());
        entity.setFlagAprovado(dto.getFlagAprovado());
        return entity;
    }

}
