package com.consol.api.repository;

import com.consol.api.entity.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {

    List<Doacao> findByDataDoacaoBetween(LocalDateTime inicio, LocalDateTime fim);
    @Query("SELECT d FROM Doacao d JOIN FETCH d.titular t JOIN FETCH t.familia f WHERE LOWER(t.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")    List<Doacao> findByTitularNomeContainsIgnoreCase(String nome);
    List<Doacao> findByDataDoacaoBetweenAndFlagDoacaoEntregue(LocalDateTime inicio, LocalDateTime fim, Byte flag);

    @Query("SELECT d FROM Doacao d JOIN FETCH d.titular t JOIN FETCH t.familia f ORDER BY d.dataDoacao DESC")
    List<Doacao> findByTitularFamilia();

    @Query("SELECT d FROM Doacao d JOIN FETCH d.titular t JOIN FETCH t.familia f ORDER BY d.dataDoacao")
    List<Doacao> findByTitularFamiliaDecrescente();

}
