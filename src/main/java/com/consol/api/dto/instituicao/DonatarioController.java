package com.consol.api.dto.instituicao;

import com.consol.api.repository.DonatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donatarios")
public class DonatarioController {

    @Autowired
    private DonatarioRepository donatarioRepository;
}
