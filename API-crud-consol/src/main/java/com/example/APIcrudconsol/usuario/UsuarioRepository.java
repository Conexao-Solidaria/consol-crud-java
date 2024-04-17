package com.example.APIcrudconsol.usuario;

import com.example.APIcrudconsol.instituicaoFamilia.InstituicaoFamilia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
