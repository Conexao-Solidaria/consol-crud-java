package com.example.APIcrudconsol.donatario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/donatarios")
public class DonatarioController {

    @Autowired
    private DonatarioRepositorio donatarioRepositorio;
}
