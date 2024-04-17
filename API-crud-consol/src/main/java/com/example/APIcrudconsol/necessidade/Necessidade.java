package com.example.APIcrudconsol.necessidade;

import jakarta.persistence.*;

@Entity
public class Necessidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNecessidade;

    private String tipo;

    private String descricao;

    private String fkInstituicao;


    public int getIdNecessidade() {
        return idNecessidade;
    }

    public void setIdNecessidade(int idNecessidade) {
        this.idNecessidade = idNecessidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFkInstituicao() {
        return fkInstituicao;
    }

    public void setFkInstituicao(String fkInstituicao) {
        this.fkInstituicao = fkInstituicao;
    }
}
