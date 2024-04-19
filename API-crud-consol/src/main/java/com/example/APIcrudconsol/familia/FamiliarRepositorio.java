package com.example.APIcrudconsol.familiar;

import com.example.APIcrudconsol.familiar.Familiar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface FamiliarRepositorio extends JpaRepository<Familiar, Integer> {
    @Query("SELECT f FROM Familiar f")
    Collection<Familiar> encontrarTodos();
}
