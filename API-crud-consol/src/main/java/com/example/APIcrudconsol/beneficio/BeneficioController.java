package com.example.APIcrudconsol.beneficio;

import com.example.APIcrudconsol.donatario.DonatarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BeneficioController {

    @Autowired
    private BeneficioRepositorio beneficioRepositorio;
    @Autowired
    private DonatarioRepositorio donatarioRepositorio;

    @PostMapping()
    public ResponseEntity adicionar(@RequestBody Beneficio beneficio) throws ParseException {
        if(donatarioRepositorio.findById(beneficio.getFkUsuario()).isEmpty() || beneficio.getFkUsuario() == null){
            return ResponseEntity.status(400).build();
        }

        beneficioRepositorio.save(beneficio);
        return ResponseEntity.status(201).build();
    }

    @GetMapping()
    public ResponseEntity<List<Beneficio>> buscarTodos(){
        List<Beneficio> beneficios = new ArrayList<>();
        beneficios = (List<Beneficio>) beneficioRepositorio.encontrarTodos();

        if(beneficios.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(beneficios);
    }

    @PutMapping()
    public ResponseEntity<Beneficio> atualizar(@RequestBody Beneficio beneficio){
        if(pegarbeneficioFk(beneficio.getFkUsuario()) == null){
            return ResponseEntity.status(404).build();
        }

        Beneficio beneficioSalvo = beneficioRepositorio.save(beneficio);
        return ResponseEntity.status(200).body(beneficioRepositorio.save(beneficioSalvo));
    }

    @DeleteMapping("/{fk}")
    public ResponseEntity delete(@PathVariable int fk) {
        if(pegarbeneficioFk(fk) == null){
            return ResponseEntity.status(404).build();
        }
        beneficioRepositorio.deleteById(fk);
        return ResponseEntity.status(200).build();
    }

    public Beneficio pegarbeneficioFk(int fk){
        Optional<Beneficio> beneficio = beneficioRepositorio.findById(fk);
        return beneficio.orElse(null);
    }
}
