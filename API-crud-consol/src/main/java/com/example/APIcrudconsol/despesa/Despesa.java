package com.example.APIcrudconsol.despesa;

import jakarta.persistence.*;
@Entity
public class Despesa {
    @Id
    private Integer fkFamilia;

    private String tipo;

    private Double gasto;

    public Integer getFkFamilia() {
        return fkFamilia;
    }

    public void setFkFamilia(Integer fkFamilia) {
        this.fkFamilia = fkFamilia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getGasto() {
        return gasto;
    }

    public void setGasto(Double gasto) {
        this.gasto = gasto;
    }
}
