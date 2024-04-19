package com.example.APIcrudconsol.despesa;

import com.example.APIcrudconsol.donatario.DonatarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private com.example.APIcrudconsol.despesa.DespesaRepositorio DespesaRepositorio;
    @Autowired
    private DonatarioRepositorio donatarioRepositorio;

}
