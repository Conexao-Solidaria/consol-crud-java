package com.example.APIcrudconsol.registro;

import com.example.APIcrudconsol.usuario.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registro")
public class RegistroController {
    @Autowired
    private RegistroRepositorio registroRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PostMapping()
    public ResponseEntity adicionar(@RequestBody RegistroVisita registro) throws ParseException {
        if(usuarioRepositorio.findById(registro.getFkUsuario()).isEmpty() || registro.getFkUsuario() == null){
            return ResponseEntity.status(400).build();
        }

        registroRepositorio.save(registro);
        return ResponseEntity.status(201).build();
    }

    @GetMapping()
    public ResponseEntity<List<RegistroVisita>> buscarTodos(){
        List<RegistroVisita> registros = new ArrayList<>();
        registros = (List<RegistroVisita>) registroRepositorio.encontrarTodos();

        if(registros.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(registros);
    }

    @PutMapping()
    public ResponseEntity<RegistroVisita> atualizar(@RequestBody RegistroVisita registro){
        if(pegarRegistroFk(registro.getFkUsuario()) == null){
            return ResponseEntity.status(404).build();
        }

        RegistroVisita registroSalvo = registroRepositorio.save(registro);
        return ResponseEntity.status(200).body(registroRepositorio.save(registroSalvo));
    }

    @DeleteMapping("/{fk}")
    public ResponseEntity delete(@PathVariable int fk) {
        if(pegarRegistroFk(fk) == null){
            return ResponseEntity.status(404).build();
        }
        registroRepositorio.deleteById(fk);
        return ResponseEntity.status(200).build();
    }

    public RegistroVisita pegarRegistroFk(int fk){
        Optional<RegistroVisita> Registro = registroRepositorio.findById(fk);
        return Registro.orElse(null);
    }
    
}
