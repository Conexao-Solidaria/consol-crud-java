package com.example.APIcrudconsol.usuario.autenticacao;

import com.example.APIcrudconsol.usuario.Usuario;
import com.example.APIcrudconsol.usuario.UsuarioRepository;
import com.example.APIcrudconsol.usuario.dto.UsuarioDetalhesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
