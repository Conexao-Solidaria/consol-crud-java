package com.consol.api.dto;

import com.consol.api.entity.Instituicao;
import com.consol.api.entity.Usuario;

import java.util.List;

public class UsuarioMapper {
    public static Usuario cadastrarDtoParaUsuario(UsuarioCadastroDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha()); // Importante para cadastrar o usuário com a senha
        usuario.setCpf(dto.getCpf());
        usuario.setCoordenador(dto.getCoordenador());
        Instituicao instituicao = new Instituicao();
        instituicao.setId(dto.getFkInstituicao());
        usuario.setInstituicao(instituicao);
        return usuario;
    }

    public static UsuarioConsultaDto usuarioParaConsultaDto(Usuario usuario) {
        UsuarioConsultaDto dto = new UsuarioConsultaDto();
        dto.setIdUsuario(usuario.getId());
        dto.setNomeUsuario(usuario.getNomeUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setCpf(usuario.getCpf());
        dto.setCoordenador(usuario.isCoordenador());
        if (usuario.getInstituicao() != null) {
            dto.setFkInstituicao(usuario.getInstituicao().getId());
        }
        return dto;
    }

    public static Usuario atualizarDtoParaUsuario(UsuarioAtualizarDto dto, Usuario usuario) {
        if (dto.getNomeUsuario() != null) {
            usuario.setNomeUsuario(dto.getNomeUsuario());
        }

        if (dto.getEmail() != null) {
            usuario.setEmail(dto.getEmail());
        }

        if (dto.getCoordenador() != null) {
            usuario.setCoordenador(dto.getCoordenador());
        }

        if (dto.getCpf() != null) {
            usuario.setCpf(dto.getCpf());
        }

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

    public static List<UsuarioConsultaDto> listagemDtoList(List<Usuario> usuarios){
        return usuarios.stream().map(UsuarioMapper::usuarioParaConsultaDto).toList();
    }
}
