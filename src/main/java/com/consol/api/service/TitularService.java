package com.consol.api.service;

import com.consol.api.entity.Titular;
import com.consol.api.entity.Familia;
import com.consol.api.entity.exception.RequisicaoIncorretaException;
import com.consol.api.repository.TitularRepository;
import com.consol.api.utils.Lambda;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TitularService {

    private final TitularRepository repository;

    private final FamiliaService familiaService;

    private final Region region = Region.US_EAST_1;
    private final LambdaClient lambdaClient = LambdaClient.builder().region(region).build();

    public List<Titular> listar() {
        return repository.findAll();
    }

    public Titular salvar(Titular titular, Integer idFamilia) {
        Familia familia = familiaService.porId(idFamilia);

        if (titular.getTrabalhando() != (byte) 0 && titular.getTrabalhando() != (byte) 1)
            throw new RequisicaoIncorretaException("Titular");

        titular.setFamilia(familia);
        return repository.save(titular);
    }

    public Titular porId(int id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public List<Titular> listarPorNome(String nome) {
        return repository.findByNomeContainsIgnoreCase(nome);
    }

    public Titular atualizar(int id, Titular titular) {
        Titular titularAtualizado = porId(id);

        titularAtualizado.setNome(titular.getNome());
        titularAtualizado.setEstadoCivil(titular.getEstadoCivil());
        titularAtualizado.setEscolaridade(titular.getEscolaridade());
        titularAtualizado.setTelefone1(titular.getTelefone1());
        titularAtualizado.setTelefone2(titular.getTelefone2());
        titularAtualizado.setTrabalhando(titular.getTrabalhando());
        titularAtualizado.setOcupacao(titular.getOcupacao());

        return repository.save(titularAtualizado);
    }

    public void deletar(int id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }

    public Boolean existById(int id) {
        return repository.existsById(id);
    }

    public Integer qtdCriancas(LocalDate dataAtual) {
        LocalDate dataBase = dataAtual.minusYears(12);
        return repository.countByDataNascimentoAfter(dataBase);
    }

    public Integer zeroADozeAnos(LocalDate dataAtual) {
        LocalDate dataBase = dataAtual.minusYears(12);
        return repository.countByDataNascimentoBetween(dataBase, dataAtual);
    }

    public Integer trezeVinteCinco(LocalDate dataAtual) {
        LocalDate dataIncio = dataAtual.minusYears(25);
        LocalDate dataFim = dataAtual.minusYears(13);
        return repository.countByDataNascimentoBetween(dataIncio, dataFim);
    }

    public Integer vinteCincoASessenta(LocalDate dataAtual) {
        LocalDate dataInicio = dataAtual.minusYears(60);
        LocalDate dataFim = dataAtual.minusYears(25);

        return repository.countByDataNascimentoBetween(dataInicio, dataFim);
    }

    public Integer maisSessenta(LocalDate data) {
        LocalDate dataBase = data.minusYears(60);
        return repository.countByDataNascimentoBefore(dataBase);
    }

    public void atualizarFoto(int id, byte[] referenciaArquivoFoto) {
        final Optional<Titular> optTitular = repository.findById(id);

        if (optTitular.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        final Titular titular = optTitular.get();

        final String nomeTitular = titular.getNome();

        final String foto = Base64.getEncoder().encodeToString(referenciaArquivoFoto);
        final Lambda lambda = Lambda.FILE_UPLOAD;

        invokeLambdaFunction(lambda, nomeTitular, foto);
    }

    public byte[] getFoto(int id) {
        final Optional<Titular> optTitular = repository.findById(id);

        if (optTitular.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        final Titular titular = optTitular.get();
        final String nomeTitular = titular.getNome();

        final Lambda lambda = Lambda.FILE_DOWNLOAD;

        final String base64Image = responseToImage(invokeLambdaFunction(lambda, nomeTitular));

        return Base64.getDecoder().decode(base64Image);
    }

    private String invokeLambdaFunction(final Lambda lambda, final String nomeCliente) {
        final String payload = String.format("{ \"nome\": \"%s\" }", nomeCliente);

        final InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName(lambda.getName())
                .payload(SdkBytes.fromUtf8String(payload))
                .build();

        final InvokeResponse invokeResponse = lambdaClient.invoke(invokeRequest);

        return invokeResponse.payload().asUtf8String();
    }

    private String invokeLambdaFunction(final Lambda lambda, final String nomeCliente, final String base64EncodedImage) {
        final String payload = String.format("{ \"nome\": \"%s\", \"img\": \"%s\" }", nomeCliente, base64EncodedImage);

        final InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName(lambda.getName())
                .payload(SdkBytes.fromUtf8String(payload))
                .build();

        final InvokeResponse invokeResponse = lambdaClient.invoke(invokeRequest);

        final String response = invokeResponse.payload().asUtf8String();
        return response;
    }

    private String responseToImage(final String response) {
        try {
            final JsonNode jsonResponse = new ObjectMapper().readTree(response);
            return jsonResponse.path("image").asText();
        } catch (final Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}