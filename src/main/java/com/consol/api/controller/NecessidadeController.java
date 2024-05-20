package com.consol.api.controller;

import com.consol.api.repository.DonatarioRepository;
import com.consol.api.repository.FamiliarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/necessidades")
public class NecessidadeController {

    @Autowired
    private FamiliarRepository familiarRepository;
    @Autowired
    private DonatarioRepository donatarioRepository;

}
