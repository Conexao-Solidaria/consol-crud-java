package com.consol.api.repository;

import com.consol.api.entity.InstituicaoFamilia;
import com.consol.api.entity.Necessidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NecessidadeRepository extends JpaRepository<Necessidade, Integer> {
    List<Necessidade> findByTipoContainsIgnoreCase(String tipo);
}
