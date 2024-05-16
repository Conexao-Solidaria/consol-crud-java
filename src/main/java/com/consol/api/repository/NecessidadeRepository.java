package com.consol.api.repository;

import com.consol.api.entity.InstituicaoFamilia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NecessidadeRepository extends JpaRepository<InstituicaoFamilia, Integer> {

}
