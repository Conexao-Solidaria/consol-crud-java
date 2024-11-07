package com.consol.api.repository;

import com.consol.api.entity.Titular;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface TitularRepository extends JpaRepository<Titular, Integer> {
    @Query("SELECT u FROM Titular u")
    Collection<Titular> encontrarTodos();

    List<Titular> findByNomeContainsIgnoreCase(String nome);
    Integer countByDataNascimentoAfter(LocalDate dataBase);
    Integer countByDataNascimentoBetween(LocalDate dataBase, LocalDate atual);
    Integer countByDataNascimentoBefore(LocalDate dataBase);
}
