package com.example.APIcrudconsol.doacoes;

import com.example.APIcrudconsol.donatario.DonatarioRepositorio;
import com.example.APIcrudconsol.familiar.Familiar;
import com.example.APIcrudconsol.familiar.FamiliarRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/familiar")
public class DoacoesController {

    @Autowired
    private FamiliarRepositorio familiarRepositorio;
    @Autowired
    private DonatarioRepositorio donatarioRepositorio;

    @PostMapping()
    public ResponseEntity adicionar(@RequestBody Familiar familiar) throws ParseException {
        if(donatarioRepositorio.findById(familiar.getFkUsuario()).isEmpty() || familiar.getFkUsuario() == null){
            return ResponseEntity.status(400).build();
        }

        familiarRepositorio.save(familiar);
        return ResponseEntity.status(201).build();
    }

    @GetMapping()
    public ResponseEntity<List<Familiar>> buscarTodos(){
        List<Familiar> familiares = new ArrayList<>();
        familiares = (List<Familiar>) familiarRepositorio.encontrarTodos();

        if(familiares.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(familiares);
    }

    @PutMapping()
    public ResponseEntity<Familiar> atualizar(@RequestBody Familiar familiar){
        if(pegarFamiliarFk(familiar.getFkUsuario()) == null){
            return ResponseEntity.status(404).build();
        }
        Familiar familiarSalvo = familiarRepositorio.save(familiar);
        return ResponseEntity.status(200).body(familiarRepositorio.save(familiar));
    }

    @DeleteMapping("/{fk}")
    public ResponseEntity delete(@PathVariable int fk) {
        if(pegarFamiliarFk(fk) == null){
            return ResponseEntity.status(404).build();
        }
        familiarRepositorio.deleteById(fk);
        return ResponseEntity.status(200).build();
    }

    public Familiar pegarFamiliarFk(int fk){
        Optional<Familiar> familiar = familiarRepositorio.findById(fk);
        return familiar.orElse(null);
    }
}
