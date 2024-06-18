package com.consol.api.repository;

import com.consol.api.dto.usuario.UsuarioConsultaDto;
import com.consol.api.entity.Instituicao;
import com.consol.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByInstituicao(Instituicao instituicao);
}
