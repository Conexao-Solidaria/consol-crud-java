package com.consol.api.service;

import com.consol.api.entity.Doacao;
import com.consol.api.entity.Familia;
import com.consol.api.entity.Instituicao;
import com.consol.api.entity.exception.EntidadeNaoEncontradaException;
import com.consol.api.repository.DoacaoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.maven.surefire.shared.compress.harmony.pack200.PackingUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoacaoService {

    private final DoacaoRepository repository;
    private final InstituicaoService instituicaoService;
    private final TitularService titularService;

    public Doacao salvar(Doacao doacao, int idTitular, Integer idInstituicao ) {
         doacao.setInstituicao(instituicaoService.consultarPorId(idInstituicao));
         doacao.setTitular(titularService.porId(idTitular));

        return repository.save(doacao);
    }

    public List<Doacao> listar() {
        return repository.findAll();
    }

    public Doacao listarPorId(int id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<Doacao> listarPorData(LocalDate dataDoacao) {
        LocalDateTime inicioDoDia = dataDoacao.atStartOfDay();
        LocalDateTime fimDoDia = inicioDoDia.plusDays(1).minusSeconds(1);
        return repository.findByDataDoacaoBetween(inicioDoDia,fimDoDia);
    }

    public List<Doacao> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        LocalDateTime inicioDoDia = dataInicio.atStartOfDay();
        LocalDateTime fimDoDia = dataFim.plusDays(1).atStartOfDay().minusSeconds(1);
        return repository.findByDataDoacaoBetween(inicioDoDia, fimDoDia);
    }

    public Doacao atualizarFlag(int id, Doacao doacao){
        Doacao doacaoAtualizar = listarPorId(id);

        doacaoAtualizar.setFlagDoacaoEntregue(doacao.getFlagDoacaoEntregue());
        return repository.save(doacaoAtualizar);
    }

    public Doacao atualizarDescricao(int id, Doacao doacao){
        Doacao doacaoAtualizar = listarPorId(id);
        doacaoAtualizar.setDescricao(doacao.getDescricao());

        return repository.save(doacaoAtualizar);
    }

    public Doacao confirmarDoacao(int id, Doacao doacao){
        Doacao doacaoAtualizar = listarPorId(id);
        doacaoAtualizar.setDataDoacao(doacao.getDataDoacao());
        doacaoAtualizar.setFlagDoacaoEntregue((byte) 1);

        return repository.save(doacaoAtualizar);
    }

    public void deletar(int id){
        if (!repository.existsById(id)) throw new EntidadeNaoEncontradaException("Doação");
        repository.deleteById(id);
    }

    public List<Doacao> listarPorNome(String nome){
        return repository.findByTitularNomeContainsIgnoreCase(nome);
    }

}
