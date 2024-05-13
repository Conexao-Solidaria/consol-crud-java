package com.example.APIcrudconsol.doacoes;

import com.example.APIcrudconsol.donatario.DonatarioRepositorio;
import com.example.APIcrudconsol.familia.FamiliarRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doacoes")
public class DoacoesController {

    @Autowired
    private FamiliarRepositorio familiarRepositorio;
    @Autowired
    private DonatarioRepositorio donatarioRepositorio;
}
