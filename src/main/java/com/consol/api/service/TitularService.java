package com.consol.api.service;

import com.consol.api.entity.Donatario;
import com.consol.api.entity.Familia;
import com.consol.api.repository.TitularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonatarioService {

    private final TitularRepository repository;

    private final FamiliaService familiaService;

    public List<Donatario> listar() {
        return repository.findAll();
    }

    public Donatario salvar(Donatario donatario, Integer idFamilia) {
        Familia familia = familiaService.porId(idFamilia);

        donatario.setFamilia(familia);
        return repository.save(donatario);
    }

    public Donatario porId(int id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<Donatario> listarPorNome(String nome) {
        return repository.findByNomeContainsIgnoreCase(nome);
    }

    public Donatario atualizar(int id, Donatario donatario) {
        Donatario donatarioAtualizado = porId(id);

        donatarioAtualizado.setNome(donatario.getNome());
        donatarioAtualizado.setSobrenome(donatario.getSobrenome());
        donatarioAtualizado.setEstadoCivil(donatario.getEstadoCivil());
        donatarioAtualizado.setEscolaridade(donatario.getEscolaridade());
        donatarioAtualizado.setTelefone1(donatario.getTelefone1());
        donatarioAtualizado.setTelefone2(donatario.getTelefone2());
        donatarioAtualizado.setTrabalhando(donatario.getTrabalhando());
        donatarioAtualizado.setBeneficio(donatario.getBeneficio());
        donatarioAtualizado.setOcupacao(donatario.getOcupacao());

        return repository.save(donatarioAtualizado);
    }

    public void deletar(int id) {
        if (!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }
}
