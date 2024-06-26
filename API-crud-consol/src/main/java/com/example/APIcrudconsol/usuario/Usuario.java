package com.example.APIcrudconsol.usuario;

import com.example.APIcrudconsol.instituicao.Instituicao;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "coordenador")
    private Boolean coordenador;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "cpf")
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "fk_instituicao")
    private Instituicao FkInstituicao;
}
