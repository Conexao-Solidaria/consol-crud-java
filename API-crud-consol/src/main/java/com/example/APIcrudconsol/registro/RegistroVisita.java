package com.example.APIcrudconsol.registro;

import jakarta.persistence.*;

@Entity
public class RegistroVisita {
    @Column(name = "datavisita")
    private String dataVisita;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "fkusuario")
    @Id
    private Integer fkUsuario;

    public String getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(String dataVisita) {
        this.dataVisita = dataVisita;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
