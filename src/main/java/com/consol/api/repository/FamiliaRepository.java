package com.consol.api.repository;

import com.consol.api.entity.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface FamiliaRepository extends JpaRepository<Familia, Integer> {
    @Query("SELECT f FROM Familia f")
    Collection<Familia> encontrarTodos();

    Familia findByNomeAndCepEquals(String nome, String cep);
}
