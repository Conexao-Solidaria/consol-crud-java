package com.example.APIcrudconsol.instituicaoFamilia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class InstituicaoFamilia {
    @Id
    private Integer fkInstituicao;

    @Id
    private Integer fkFamilia;
}
