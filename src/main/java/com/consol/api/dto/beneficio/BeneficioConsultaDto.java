package com.consol.api.dto.beneficio;

import lombok.Data;

@Data
public class BeneficioConsultaDto {
    private Integer id;
    private String nome;
    private Double valor;
    private Integer idDonatario;


}
