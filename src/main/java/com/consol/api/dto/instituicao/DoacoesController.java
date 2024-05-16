package com.consol.api.dto.instituicao;

import com.consol.api.repository.DonatarioRepository;
import com.consol.api.repository.FamiliarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doacoes")
public class DoacoesController {

    @Autowired
    private FamiliarRepository familiarRepository;
    @Autowired
    private DonatarioRepository donatarioRepository;
}
