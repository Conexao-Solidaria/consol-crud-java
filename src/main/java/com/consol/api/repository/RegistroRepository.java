package com.consol.api.repository;

import com.consol.api.entity.RegistroVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface RegistroRepository extends JpaRepository<RegistroVisita, Integer> {
    @Query("SELECT r FROM RegistroVisita r")
    Collection<RegistroVisita> encontrarTodos();
}
