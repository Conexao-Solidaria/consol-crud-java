package com.example.APIcrudconsol.usuario;

import com.example.APIcrudconsol.instituicao.Instituicao;
import com.example.APIcrudconsol.instituicao.InstituicaoRepository;
import com.example.APIcrudconsol.instituicao.dto.InstituicaoAtualizarDto;
import com.example.APIcrudconsol.instituicao.dto.InstituicaoConsultaDto;
import com.example.APIcrudconsol.instituicao.dto.InstituicaoMapper;
import com.example.APIcrudconsol.usuario.Service.UsuarioService;
import com.example.APIcrudconsol.usuario.dto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private InstituicaoRepository instituicaoRepository;
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioConsultaDto> criar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto){
        if(usuarioCadastroDto == null) return ResponseEntity.status(400).build();
        if(!instituicaoRepository.existsById(usuarioCadastroDto.getFkInstituicao())) return ResponseEntity.status(400).build();

        Usuario usuario = UsuarioMapper.cadastrarDtoParaUsuario(usuarioCadastroDto);

        Usuario usuarioSalvar = usuarioRepository.save(usuario);

        UsuarioConsultaDto usuarioConsultaDto = UsuarioMapper.usuarioParaConsultaDto(usuarioSalvar);

        return ResponseEntity.status(201).body(usuarioConsultaDto);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioConsultaDto>> listagemUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        if(usuarios.isEmpty()) return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(UsuarioMapper.listagemDtoList(usuarios));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioConsultaDto> consultarPorId(@PathVariable Integer id){
        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(id);

        if(usuarioBuscado.isEmpty()) return ResponseEntity.status(404).build();

        UsuarioConsultaDto dto = UsuarioMapper.usuarioParaConsultaDto(usuarioBuscado.get());

        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioConsultaDto> atualizar(@RequestBody @Valid UsuarioAtualizarDto usuarioAtualizarDto, @PathVariable Integer id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        //O  java por algum motivo quando atualiza o banco muda a variavel, esse foi o metodo que achei

        if(usuarioOptional.isEmpty()) return ResponseEntity.status(404).build();

        Usuario usuarioBuscado = usuarioOptional.get();

        Usuario usuario = UsuarioMapper.atualizarDtoParaUsuario(usuarioAtualizarDto, usuarioBuscado);


        Usuario eventoAtualizado = usuarioRepository.save(usuario);

        UsuarioConsultaDto dto = UsuarioMapper.usuarioParaConsultaDto(eventoAtualizado);

        return ResponseEntity.status(200).body(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> apagarPorId(@PathVariable Integer id){
        if(!usuarioRepository.existsById(id)){
            return ResponseEntity.status(404).build();
        }

        usuarioRepository.deleteById(id);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(
            @RequestBody UsuarioLoginDto usuarioLoginDto
    ) {
        UsuarioTokenDto usuarioToken = this.usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.ok(usuarioToken);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioConsultaDto> cadastrar(
            @RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto
    ) {
        if(usuarioCadastroDto == null) return ResponseEntity.status(400).build();
        if(!instituicaoRepository.existsById(usuarioCadastroDto.getFkInstituicao())) return ResponseEntity.status(400).build();

        usuarioService.criar(usuarioCadastroDto);

        Usuario usuario = UsuarioMapper.cadastrarDtoParaUsuario(usuarioCadastroDto);
        UsuarioConsultaDto usuarioConsultaDto = UsuarioMapper.usuarioParaConsultaDto(usuario);

        return ResponseEntity.status(201).body(usuarioConsultaDto);
    }
}
