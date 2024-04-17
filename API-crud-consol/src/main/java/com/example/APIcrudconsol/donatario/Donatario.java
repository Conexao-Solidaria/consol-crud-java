package com.example.APIcrudconsol.donatario;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Donatario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idBeneficiario;

    private String dataCadastro;

    private String nome;

    private String rg;

    private String cpf;

    private LocalDate dataNascimento;

    private String telefone1;

    private String telefone2;

//    @Column(name = "cep")
//    private String cep;
//
//    @Column(name = "numerocasa")
//    private int numeroCasa;

    private String estadoCivil;

    private String escolaridade;

    private Boolean trabalhando;

    private String ocupacao;

    private Integer fk_familia;

    public Integer getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(Integer idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Boolean getTrabalhando() {
        return trabalhando;
    }

    public void setTrabalhando(Boolean trabalhando) {
        this.trabalhando = trabalhando;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public Integer getFk_familia() {
        return fk_familia;
    }

    public void setFk_familia(Integer fk_familia) {
        this.fk_familia = fk_familia;
    }
}
