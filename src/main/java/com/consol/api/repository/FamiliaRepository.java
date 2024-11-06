package com.consol.api.repository;

import com.consol.api.entity.Familia;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;

public interface FamiliaRepository extends JpaRepository<Familia, Integer> {
    @Query("SELECT f FROM Familia f")
    Collection<Familia> encontrarTodos();

    Familia findByNomeAndCepEquals(String nome, String cep);
    Integer countBy();
    Integer countByDataCadastroBefore(LocalDate dataBase);
}
