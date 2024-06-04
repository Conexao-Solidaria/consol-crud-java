package com.consol.api.controller;

import com.consol.api.dto.beneficio.BeneficioConsultaDto;
import com.consol.api.service.BeneficioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beneficio")
@RequiredArgsConstructor
public class BeneficioController {
    private final BeneficioService beneficioService;

     @GetMapping
    public ResponseEntity<BeneficioConsultaDto> listar(){
         return null;
     }


}
