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
@RequestMapping("/usuario")
public class DonatarioController {

    @Autowired
    private DonatarioRepositorio donatarioRepositorio;

    @PostMapping()
    public ResponseEntity adicionar(@RequestBody Donatario donatario) throws ParseException {
        if(donatario == null){
            return ResponseEntity.status(400).build();
        }
        LocalDate diaHoje = LocalDate.now();
        donatario.setDataCadastro(diaHoje.toString());

        donatarioRepositorio.save(donatario);
        return ResponseEntity.status(201).build();
    }

    @GetMapping()
    public ResponseEntity<List<Donatario>> buscarTodos(){
        List<Donatario> donatarios = new ArrayList<>();
        donatarios = (List<Donatario>) donatarioRepositorio.encontrarTodos();

        if(donatarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(donatarios);
    }

    @PutMapping()
    public ResponseEntity<Donatario> atualizar(@RequestBody Donatario donatario){
        if(pegarPessoaId(donatario.getIdUsuario()) == null){
            return ResponseEntity.status(404).build();
        }
        Donatario donatarioSalvo = donatarioRepositorio.save(donatario);
        return ResponseEntity.status(200).body(donatarioRepositorio.save(donatario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if(pegarPessoaId(id) == null){
            return ResponseEntity.status(404).build();
        }
        donatarioRepositorio.deleteById(id);
        return ResponseEntity.status(200).build();
    }

    public Donatario pegarPessoaId(int id){
        Optional<Donatario> usuario = donatarioRepositorio.findById(id);
        return usuario.orElse(null);
    }
}
