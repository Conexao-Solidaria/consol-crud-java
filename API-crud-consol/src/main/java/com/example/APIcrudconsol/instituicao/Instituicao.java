package com.example.APIcrudconsol.instituicao;

import jakarta.persistence.*;

@Entity
public class Instituicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInstituicao;

    private String nomeInstituicao;

    @Column(name = "cep", unique = true)
    private String cep;

    @Column(name = "numero_imovel", unique = true)
    private String numeroImovel;

    private String descricao;

    @Lob
    @Column(name = "foto_perfil")
    private byte[] fotoPerfil;
}
