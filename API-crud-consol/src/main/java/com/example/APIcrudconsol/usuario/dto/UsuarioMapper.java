package com.example.APIcrudconsol.usuario.dto;

import com.example.APIcrudconsol.instituicao.Instituicao;
import com.example.APIcrudconsol.usuario.Usuario;

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
        instituicao.setIdInstituicao(dto.getFkInstituicao());
        usuario.setFkInstituicao(instituicao);
        return usuario;
    }

    public static UsuarioConsultaDto usuarioParaConsultaDto(Usuario usuario) {
        UsuarioConsultaDto dto = new UsuarioConsultaDto();
        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNomeUsuario(usuario.getNomeUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setCpf(usuario.getCpf());
        dto.setCoordenador(usuario.getCoordenador());
        if (usuario.getFkInstituicao() != null) {
            dto.setFkInstituicaoId(usuario.getFkInstituicao().getIdInstituicao());
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

    public static Usuario loginDtoParaUsuario(UsuarioLoginDto dto){
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCoordenador(dto.getCoordenador());
        return usuario;
    }

    public static List<UsuarioConsultaDto> listagemDtoList(List<Usuario> usuarios){
        return usuarios.stream().map(UsuarioMapper::usuarioParaConsultaDto).toList();
    }
}
