package com.consol.api.service;

import com.consol.api.dto.usuario.UsuarioDetalhesDto;
import com.consol.api.entity.Usuario;
import com.consol.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(username);

        if (usuarioOpt.isEmpty()) throw
                new UsernameNotFoundException(String.format("usuario: %s nao encontrado", username));

        return new UsuarioDetalhesDto(usuarioOpt.get());
    }
}
