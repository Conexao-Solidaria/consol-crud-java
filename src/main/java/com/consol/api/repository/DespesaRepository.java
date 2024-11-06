package com.consol.api.repository;

import com.consol.api.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Integer> {

    List<Despesa> findByFamiliaId(Integer id);

}
