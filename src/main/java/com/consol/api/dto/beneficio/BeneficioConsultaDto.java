package com.consol.api.dto.beneficio;

import com.consol.api.entity.Donatario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BeneficioConsultaDto {
    private Integer id;
    private String nome;
    private Double valor;
    private Integer idDonatario;


}
