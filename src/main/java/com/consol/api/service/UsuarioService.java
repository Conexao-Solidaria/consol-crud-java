package com.consol.api.service;

import com.consol.api.configuration.security.jwt.GerenciadorTokenJwt;
import com.consol.api.dto.usuario.UsuarioCadastroDto;
import com.consol.api.dto.usuario.UsuarioLoginDto;
import com.consol.api.dto.usuario.UsuarioMapper;
import com.consol.api.dto.usuario.UsuarioTokenDto;
import com.consol.api.entity.*;
import com.consol.api.entity.exception.EntidadeNaoEncontradaException;
import com.consol.api.repository.FamiliaRepository;
import com.consol.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repository;
    private final InstituicaoService instituicaoService;

    public Usuario porId(int id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario criar(Usuario usuario, int idInstituicao) {
        Instituicao instituicao = instituicaoService.consultarPorId(idInstituicao);

        usuario.setInstituicao(instituicao);

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return this.usuarioRepository.save(usuario);
    }

    public Usuario atualizar(int idUsuario, Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (usuarioOptional.isEmpty()) throw new EntidadeNaoEncontradaException("Usuario");

        if (usuario.getNomeUsuario() == null) {
            usuario.setNomeUsuario(usuarioOptional.get().getNomeUsuario());
        }
        if (usuario.getEmail() == null){
            usuario.setEmail(usuarioOptional.get().getEmail());
        }


        usuario.setId(usuarioOptional.get().getId());
        usuario.setInstituicao(usuarioOptional.get().getInstituicao());

        return usuarioRepository.save(usuario);
    }

    public void deletar(int idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) throw new EntidadeNaoEncontradaException("Usuario");
        usuarioRepository.deleteById(idUsuario);
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    public Usuario atualizarFlag(int id, Usuario usuario){
        Usuario usuarioAtualizar = porId(id);

        usuarioAtualizar.setFlagAprovado(usuario.getFlagAprovado());
        return repository.save(usuarioAtualizar);
    }
}
