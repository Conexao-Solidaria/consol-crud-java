package com.consol.api.repository;

import com.consol.api.entity.Titular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface TitularRepository extends JpaRepository<Titular, Integer> {
    @Query("SELECT u FROM Titular u")
    Collection<Titular> encontrarTodos();

    List<Titular> findByNomeContainsIgnoreCase(String nome);
}
