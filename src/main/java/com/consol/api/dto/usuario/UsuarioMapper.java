package com.consol.api.dto.usuario;

import com.consol.api.dto.familia.FamiliaAtualizarFlagDto;
import com.consol.api.dto.instituicao.InstituicaoAtualizarDto;
import com.consol.api.entity.Familia;
import com.consol.api.entity.Instituicao;
import com.consol.api.entity.Usuario;

import java.util.List;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioCadastroDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha()); // Importante para cadastrar o usuário com a senha
        usuario.setCpf(dto.getCpf());
        usuario.setFlagAprovado(dto.getFlagAprovado());
        usuario.setCoordenador(dto.getCoordenador());
        Instituicao instituicao = new Instituicao();
        instituicao.setId(dto.getFkInstituicao());
        usuario.setInstituicao(instituicao);
        return usuario;
    }

    public static UsuarioConsultaDto toDto(Usuario usuario) {
        UsuarioConsultaDto dto = new UsuarioConsultaDto();
        dto.setIdUsuario(usuario.getId());
        dto.setNomeUsuario(usuario.getNomeUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setCpf(usuario.getCpf());
        dto.setCoordenador(usuario.isCoordenador());
        dto.setFlagAprovado(usuario.getFlagAprovado());
        if (usuario.getInstituicao() != null) {
            dto.setFkInstituicao(usuario.getInstituicao().getId());
        }
        return dto;
    }

    public static Usuario toEntity(UsuarioAtualizarDto dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();

        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setCpf(dto.getCpf());
        usuario.setEmail(dto.getEmail());
        usuario.setCoordenador(dto.getCoordenador());

        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNomeUsuario());
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
}
