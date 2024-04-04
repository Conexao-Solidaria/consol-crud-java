package com.example.APIcrudconsol.despesa;

import com.example.APIcrudconsol.usuario.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

    @Autowired
    private com.example.APIcrudconsol.despesa.DespesaRepositorio DespesaRepositorio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PostMapping()
    public ResponseEntity adicionar(@RequestBody Despesa despesa) throws ParseException {
        if(usuarioRepositorio.findById(despesa.getFkUsuario()).isEmpty() || despesa.getFkUsuario() == null){
            return ResponseEntity.status(400).build();
        }

        DespesaRepositorio.save(despesa);
        return ResponseEntity.status(201).build();
    }

    @GetMapping()
    public ResponseEntity<List<Despesa>> buscarTodos(){
        List<Despesa> despesas = new ArrayList<>();
        despesas = (List<Despesa>) DespesaRepositorio.encontrarTodos();

        if(despesas.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(despesas);
    }

    @PutMapping()
    public ResponseEntity<Despesa> atualizar(@RequestBody Despesa despesa){
        if(pegarDespesaFk(despesa.getFkUsuario()) == null){
            return ResponseEntity.status(404).build();
        }

        Despesa despesaSalvo = DespesaRepositorio.save(despesa);
        return ResponseEntity.status(200).body(DespesaRepositorio.save(despesaSalvo));
    }

    @DeleteMapping("/{fk}")
    public ResponseEntity delete(@PathVariable int fk) {
        if(pegarDespesaFk(fk) == null){
            return ResponseEntity.status(404).build();
        }
        DespesaRepositorio.deleteById(fk);
        return ResponseEntity.status(200).build();
    }

    public Despesa pegarDespesaFk(int fk){
        Optional<Despesa> despesa = DespesaRepositorio.findById(fk);
        return despesa.orElse(null);
    }
}
