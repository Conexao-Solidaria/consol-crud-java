package com.example.APIcrudconsol.registro;

import com.example.APIcrudconsol.donatario.DonatarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registros")
public class RegistroController {
    @Autowired
    private RegistroRepositorio registroRepositorio;
    @Autowired
    private DonatarioRepositorio donatarioRepositorio;
}
