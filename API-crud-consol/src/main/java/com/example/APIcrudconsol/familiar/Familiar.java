package com.example.APIcrudconsol.familiar;

import jakarta.persistence.*;

@Entity
public class Familiar {
    @Column(name = "nome")
    private String nome;

    @Column(name = "datanascimento")
    private String dataNascimento;

    @Column(name = "parentesco")
    private String parentesco;

    @Column(name = "rg")
    private String rg;

    @Column(name = "fkusuario")
    @Id
    private Integer fkUsuario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
