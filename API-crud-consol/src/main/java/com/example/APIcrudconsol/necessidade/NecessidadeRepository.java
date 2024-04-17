package com.example.APIcrudconsol.necessidade;

import com.example.APIcrudconsol.instituicaoFamilia.InstituicaoFamilia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NecessidadeRepository extends JpaRepository<InstituicaoFamilia, Integer> {

}
