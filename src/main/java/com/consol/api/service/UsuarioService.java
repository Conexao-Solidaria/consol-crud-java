package com.consol.api.service;

import com.consol.api.dto.usuario.UsuarioConsultaDto;
import com.consol.api.entity.Familia;
import com.consol.api.entity.Instituicao;
import com.consol.api.entity.Usuario;
import com.consol.api.entity.exception.EntidadeNaoEncontradaException;
import com.consol.api.repository.FamiliaRepository;
import com.consol.api.repository.InstituicaoRepository;
import com.consol.api.repository.UsuarioRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final InstituicaoRepository instituicaoRepository; // alterar depois

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public Usuario listarPorId(int id){
        return usuarioRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException()
        );
    }

    public List<Usuario> porIdInstuicao(int id){

        Instituicao instituicao = instituicaoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException()
        );

       return usuarioRepository.findByInstituicao(instituicao);
    }

    public Usuario salvar(Usuario usuario, int idInstituicao){
        Instituicao instituicao = instituicaoRepository.findById(idInstituicao).orElseThrow(
                () -> new EntidadeNaoEncontradaException()
        );

        usuario.setInstituicao(instituicao);

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(int id, Usuario usuario){
        Usuario usuarioAtualizar = usuarioRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException()
        );

        if (usuario.getCoordenador() != null) usuarioAtualizar.setCoordenador(usuario.getCoordenador());
        if (usuario.getEmail() != null) usuarioAtualizar.setEmail(usuario.getEmail());
        if (usuario.getSenha() != null) usuarioAtualizar.setSenha(usuario.getSenha());

        return usuarioRepository.save(usuarioAtualizar);
    }

    public void deletar(int id){
        if (!usuarioRepository.existsById(id)) throw new EntidadeNaoEncontradaException();
        usuarioRepository.deleteById(id);
    }
}
