package com.example.APIcrudconsol.beneficio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Beneficio {
    private String nome;

    private Double valor;

    @Id
    private Integer fkBeneficiario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getFkBeneficiario() {
        return fkBeneficiario;
    }

    public void setFkBeneficiario(Integer fkBeneficiario) {
        this.fkBeneficiario = fkBeneficiario;
    }
}
