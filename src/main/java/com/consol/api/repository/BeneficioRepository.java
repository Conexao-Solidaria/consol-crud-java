package com.consol.api.repository;


import com.consol.api.entity.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeneficioRepository extends JpaRepository<Beneficio, Integer> {
    List<Beneficio> findByTitularId(Integer idDonatario);
    List<Beneficio> findByTitularFamiliaId(Integer idFamilia);
}
