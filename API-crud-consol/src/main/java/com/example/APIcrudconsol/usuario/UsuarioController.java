package com.example.APIcrudconsol.usuario;

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
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PostMapping()
    public ResponseEntity adicionar(@RequestBody Usuario usuario) throws ParseException {
        if(usuario == null){
            return ResponseEntity.status(400).build();
        }
        LocalDate diaHoje = LocalDate.now();
        usuario.setDataCadastro(diaHoje.toString());

        usuarioRepositorio.save(usuario);
        return ResponseEntity.status(201).build();
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> buscarTodos(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = (List<Usuario>) usuarioRepositorio.encontrarTodos();

        if(usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(usuarios);
    }

    @PutMapping()
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario){
        if(pegarPessoaId(usuario.getIdUsuario()) == null){
            return ResponseEntity.status(404).build();
        }
        Usuario usuarioSalvo = usuarioRepositorio.save(usuario);
        return ResponseEntity.status(200).body(usuarioRepositorio.save(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if(pegarPessoaId(id) == null){
            return ResponseEntity.status(404).build();
        }
        usuarioRepositorio.deleteById(id);
        return ResponseEntity.status(200).build();
    }

    public Usuario pegarPessoaId(int id){
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        return usuario.orElse(null);
    }
}
