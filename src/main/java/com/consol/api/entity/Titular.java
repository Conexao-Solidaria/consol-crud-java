package com.consol.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Titular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_titular")
    private Integer id;

    private String nome;

    private String rg;

    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String telefone1;

    private String telefone2;

    @Column(name = "estado_civil")
    private String estadoCivil;

    private String escolaridade;

    private Byte trabalhando;

    private String ocupacao;

    @Column(name = "referencia_s3")
    private String referenciaS3;

    @ManyToOne
    @JoinColumn(name = "fk_familia")
    private Familia familia;

    @Override
    public String toString() {
        return "Titular{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", rg='" + rg + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", telefone1='" + telefone1 + '\'' +
                ", telefone2='" + telefone2 + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                ", escolaridade='" + escolaridade + '\'' +
                ", trabalhando=" + trabalhando +
                ", ocupacao='" + ocupacao + '\'' +
                '}';
    }
}
