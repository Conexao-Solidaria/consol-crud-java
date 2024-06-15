package com.consol.api.repository;

import com.consol.api.entity.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {

    List<Doacao> findByDataDoacao(LocalDate data);

    List<Doacao> findByDataDoacaoBetween(LocalDate dataInicio, LocalDate dataFim);

}
