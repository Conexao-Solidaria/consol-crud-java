package com.consol.api.dto.instituicao;

import com.consol.api.repository.DonatarioRepository;
import com.consol.api.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registros")
public class RegistroController {
    @Autowired
    private RegistroRepository registroRepository;
    @Autowired
    private DonatarioRepository donatarioRepository;
}
