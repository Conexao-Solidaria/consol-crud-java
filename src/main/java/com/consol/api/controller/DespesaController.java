package com.consol.api.controller;

import com.consol.api.repository.DonatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private com.consol.api.repository.DespesaRepository DespesaRepository;
    @Autowired
    private DonatarioRepository donatarioRepository;

}
