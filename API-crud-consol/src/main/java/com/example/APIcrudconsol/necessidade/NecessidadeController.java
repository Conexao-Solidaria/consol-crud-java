package com.example.APIcrudconsol.necessidade;

import com.example.APIcrudconsol.donatario.DonatarioRepositorio;
import com.example.APIcrudconsol.familia.FamiliarRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/necessidades")
public class NecessidadeController {

    @Autowired
    private FamiliarRepositorio familiarRepositorio;
    @Autowired
    private DonatarioRepositorio donatarioRepositorio;

}
