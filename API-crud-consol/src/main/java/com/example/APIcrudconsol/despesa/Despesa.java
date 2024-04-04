package com.example.APIcrudconsol.despesa;

import jakarta.persistence.*;
@Entity
public class Despesa {
    @Column(name = "fk_usuario")
    @Id
    private Integer fkUsuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "gasto")
    private Double gasto;

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getGasto() {
        return gasto;
    }

    public void setGasto(Double gasto) {
        this.gasto = gasto;
    }
}
