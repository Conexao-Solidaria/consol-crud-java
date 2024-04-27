package com.example.APIcrudconsol.usuario;

import com.example.APIcrudconsol.instituicaoFamilia.InstituicaoFamilia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmailAndSenhaEquals(String email, String senha);

    Optional<Usuario> findByEmail(String email);
}
