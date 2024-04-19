package com.example.APIcrudconsol.beneficio;

import com.example.APIcrudconsol.donatario.DonatarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beneficios")
public class BeneficioController {

    @Autowired
    private BeneficioRepositorio beneficioRepositorio;
    @Autowired
    private DonatarioRepositorio donatarioRepositorio;

}
