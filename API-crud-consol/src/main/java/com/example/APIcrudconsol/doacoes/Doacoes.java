package com.example.APIcrudconsol.doacoes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Doacoes {
    @Id
    private Integer idDoacoes;

    private Double peso;

    private String descricao;

    private char statusDoacao;

    private LocalDate dataDoacao;

    @Id
    private Integer fkInstituicao;

    private Integer fkDonatario;

}
