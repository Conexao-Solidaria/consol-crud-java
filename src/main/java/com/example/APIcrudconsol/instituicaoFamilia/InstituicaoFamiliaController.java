package com.example.APIcrudconsol.instituicaoFamilia;

import com.example.APIcrudconsol.donatario.DonatarioRepositorio;
import com.example.APIcrudconsol.familia.FamiliarRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instituicao-familias")
public class InstituicaoFamiliaController {

    @Autowired
    private FamiliarRepositorio familiarRepositorio;
    @Autowired
    private DonatarioRepositorio donatarioRepositorio;
}
