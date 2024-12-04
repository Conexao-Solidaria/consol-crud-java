package com.consol.api.service;

import com.consol.api.configuration.security.jwt.GerenciadorTokenJwt;
import com.consol.api.dto.usuario.UsuarioLoginDto;
import com.consol.api.dto.usuario.UsuarioMapper;
import com.consol.api.dto.usuario.UsuarioTokenDto;
import com.consol.api.entity.*;
import com.consol.api.entity.exception.ConflitoException;
import com.consol.api.entity.exception.EntidadeNaoEncontradaException;
import com.consol.api.entity.exception.RequisicaoIncorretaException;
import com.consol.api.repository.UsuarioRepository;
import io.swagger.v3.oas.models.media.UUIDSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository repository;
    private final InstituicaoService instituicaoService;

    public Usuario criar(Usuario usuario, int idInstituicao) {
        Instituicao instituicao = instituicaoService.consultarPorId(idInstituicao);

        if (repository.existsByEmail(usuario.getEmail())) throw new ConflitoException("");
        if (repository.existsByCpf(usuario.getCpf())) throw new ConflitoException("");

        usuario.setInstituicao(instituicao);
        usuario.setCoordenador((byte) 0);
        usuario.setFlagAprovado((byte) 0);

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return repository.save(usuario);
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                repository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                            () -> new RequisicaoIncorretaException("Usuario")
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario porId(int id) {
        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Usuario")
        );
    }

    public void deletar(int idUsuario) {
        if (!repository.existsById(idUsuario)) throw new EntidadeNaoEncontradaException("Usuario");
        repository.deleteById(idUsuario);
    }

    public Usuario atualizarFlag(int id, Usuario usuario){
        Usuario usuarioAtualizar = porId(id);
        usuarioAtualizar.setFlagAprovado(usuario.getFlagAprovado());
        return repository.save(usuarioAtualizar);
    }

    public Usuario atualizarCoordenador(int id, Usuario usuario){
        Usuario usuarioAtualizar = porId(id);
        usuarioAtualizar.setCoordenador(usuario.getCoordenador());
        return repository.save(usuarioAtualizar);
    }

    public Usuario atualizarFlagCoordenador(int id, Usuario usuario){
        Usuario usuarioAtualizar = porId(id);

        usuarioAtualizar.setCoordenador(usuario.getCoordenador());
        usuarioAtualizar.setFlagAprovado(usuario.getFlagAprovado());

        return repository.save(usuarioAtualizar);
    }

}
