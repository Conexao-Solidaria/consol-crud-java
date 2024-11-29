package com.consol.api.controller;

import com.consol.api.dto.doacao.*;
import com.consol.api.entity.Doacao;
import com.consol.api.entity.Instituicao;
import com.consol.api.entity.Titular;
import com.consol.api.service.DoacaoRelatoriosService;
import com.consol.api.service.DoacaoService;
import com.consol.api.service.InstituicaoService;
import com.consol.api.service.TitularService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.lambda.endpoints.internal.Value;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/doacoes")
@RequiredArgsConstructor
public class DoacaoController {

    private final DoacaoService service;
    private final DoacaoRelatoriosService serviceRelatorios;
    private final TitularService serviceTitular;

    @PostMapping("/titular/{id}/instituicao/{idInstituicao}") // idInstituicao será sempre 1
    private ResponseEntity <DoacaoConsultaDto> criar(
            @RequestBody @Valid DoacaoCadastroDto dto,
            @PathVariable int id,
            @PathVariable int idInstituicao

    ) {

        Doacao doacao = DoacaoMapper.toEntity(dto);
        Doacao doacaoSalva = service.salvar(doacao, id, idInstituicao);

        DoacaoConsultaDto doacaoConsultaDto = DoacaoMapper.toDto(doacaoSalva);

        URI uri = URI.create("/doacoes/" + doacaoConsultaDto.getId());

        return ResponseEntity.created(uri).body(doacaoConsultaDto);
    }

    @GetMapping
    private ResponseEntity <List <DoacaoConsultaDto>> listar() {
        List <Doacao> doacoes = service.listar();

        if (doacoes.isEmpty()) return ResponseEntity.status(204).build();

        List <DoacaoConsultaDto> dtos = DoacaoMapper.toDto(doacoes);

        return ResponseEntity.status(200).body(dtos);
    }

    @GetMapping("/{id}")
    private ResponseEntity <DoacaoConsultaDto> porId(
            @PathVariable Integer id
    ) {
        Doacao doacao = service.listarPorId(id);
        DoacaoConsultaDto dto = DoacaoMapper.toDto(doacao);

        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/listagem-com-familia")
    public ResponseEntity <List <DoacaoConsultaComFamiliaDto>> consultaComFamilia() {
        List <Doacao> doacaos = service.listarDoacaoFamilia();
        List <DoacaoConsultaComFamiliaDto> dto = DoacaoMapper.toDtoFamilia(doacaos);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/filtro/por-data")
    private ResponseEntity <List <DoacaoConsultaDto>> porData(
            @RequestParam LocalDate data
    ) {
        List <Doacao> doacoes = service.listarPorData(data);

        if (doacoes.isEmpty()) return ResponseEntity.noContent().build();

        List <DoacaoConsultaDto> dtos = DoacaoMapper.toDto(doacoes);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/filtro/por-periodo")
    private ResponseEntity <List <DoacaoConsultaDto>> porPeriodo(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim
    ) {
        List <Doacao> doacoes = service.listarPorPeriodo(dataInicio, dataFim);

        if (doacoes.isEmpty()) return ResponseEntity.noContent().build();

        List <DoacaoConsultaDto> dtos = DoacaoMapper.toDto(doacoes);

        return ResponseEntity.ok(dtos);
    }


    @PutMapping("/atualizar-flag/{id}")
    public ResponseEntity <DoacaoConsultaDto> atualizarFlag(
            @RequestBody @Valid DoacaoAtualizarFlagDto dto,
            @PathVariable Integer id
    ) {
        Doacao doacao = DoacaoMapper.toEntity(dto);
        Doacao doacaoAtualizada = service.atualizarFlag(id, doacao);
        DoacaoConsultaDto doacaoConsultaDto = DoacaoMapper.toDto(doacaoAtualizada);

        return ResponseEntity.ok(doacaoConsultaDto);
    }

    @PutMapping("/atualizar-descricao/{id}")
    public ResponseEntity <DoacaoConsultaDto> atualizarDescricao(
            @RequestBody @Valid DoacaoAtualizarDescricaoDto dto,
            @PathVariable Integer id
    ) {
        Doacao doacao = DoacaoMapper.toEntity(dto);
        Doacao doacaoAtualizada = service.atualizarDescricao(id, doacao);
        DoacaoConsultaDto doacaoConsultaDto = DoacaoMapper.toDto(doacaoAtualizada);

        return ResponseEntity.status(200).body(doacaoConsultaDto);

    }


    @PutMapping("/confirmar-doacao/{id}")
    public ResponseEntity <DoacaoConsultaDto> confirmarDoacao(
            @RequestBody @Valid DoacaoConfirmacaoDto dto,
            @PathVariable Integer id
    ) {
        Doacao doacao = DoacaoMapper.toEntity(dto);
        Doacao doacaoAtualizada = service.confirmarDoacao(id, doacao);
        DoacaoConsultaDto doacaoConsultaDto = DoacaoMapper.toDto(doacaoAtualizada);

        return ResponseEntity.status(200).body(doacaoConsultaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/por-nome")
    public ResponseEntity <List <DoacaoConsultaComFamiliaDto>> porNome(@RequestParam String nome) {
        List <Doacao> doacaos = service.listarPorNome(nome);

        if (doacaos.isEmpty()) return ResponseEntity.status(204).build();

        List <DoacaoConsultaComFamiliaDto> dto = DoacaoMapper.toDtoFamilia(doacaos);
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/listagem-decrescente")
    public ResponseEntity<List<DoacaoConsultaComFamiliaDto>> listagemDecrescente(){
        List<Doacao> doacaos = service.listagemDecrescente();

        if (doacaos.isEmpty()) return ResponseEntity.status(204).build();

        List<DoacaoConsultaComFamiliaDto> doacaoConsultaComFamiliaDtos = DoacaoMapper.toDtoFamilia(doacaos);
        return ResponseEntity.status(200).body(doacaoConsultaComFamiliaDtos);
    }

    @GetMapping("/baixar-csv")
    public ResponseEntity <byte[]> baixarCsv() throws IOException {
        List <Doacao> doacoes = service.listar();

        if (doacoes.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        List <String[]> data = serviceRelatorios.converterCsv(doacoes);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8)) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.write("\n");
            }
        }

        byte[] csvBytes = byteArrayOutputStream.toByteArray();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"dados.csv\"")
                .header(HttpHeaders.CONTENT_TYPE, "text/csv")
                .body(csvBytes);
    }

    @GetMapping("/baixar-txt")
    public ResponseEntity <byte[]> baixarTxt() throws IOException {
        List <Doacao> doacoes = service.listar();

        if (doacoes.isEmpty()) return ResponseEntity.status(204).build();

        List <String[]> data = serviceRelatorios.converterTxt(doacoes);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8)) {

            writer.write("00DOACAO");
            writer.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            writer.write("01");
            writer.write("\n");

            Integer qtdDoacao = 0;

            for (String[] row : data) {
                String linha =
                        String.format("%-2.2s", "01") +
                                String.format("%-5.5s", row[0]) +
                                String.format("%-200.200s", row[1]) +
                                String.format("%-19.19s", row[2]) +
                                String.format("%-1.1s", row[3]) +
                                String.format("%-5.5s", row[4]) +
                                String.format("%-5.5s", row[5]);
                writer.write(linha);
                writer.write("\n");

                qtdDoacao++;
            }

            writer.write(String.format("%-2.2s", "02"));
            writer.write(String.format("%-5.5s", String.valueOf(qtdDoacao)));
        }

        byte[] txtBytes = byteArrayOutputStream.toByteArray();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"dados.txt\"")
                .header(HttpHeaders.CONTENT_TYPE, "text/plain")
                .body(txtBytes);
    }

    @PostMapping("/upload-txt")
    public ResponseEntity <DoacaoConsultaDto> uploadTxt(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) return ResponseEntity.status(400).build();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;

            while ((line = reader.readLine()) != null) {

                if (line.startsWith("01")) {

                    String descricao = line.substring(2, 202).trim();
                    String dataDoacaoStr = line.substring(202, 221).trim();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    LocalDateTime dataDoacao = LocalDateTime.parse(dataDoacaoStr, formatter);

                    String flagStr = line.substring(221, 222).trim();
                    Byte flagDoacaoEntregue = (byte) (flagStr.equalsIgnoreCase("S") ? 1 : 0);
                    int fkTitular = Integer.parseInt(line.substring(222, 227).trim());

                    if (!serviceTitular.existById(fkTitular)) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                    }

                    Doacao doacao = new Doacao();
                    doacao.setDescricao(descricao);
                    doacao.setDataDoacao(dataDoacao);
                    doacao.setFlagDoacaoEntregue(flagDoacaoEntregue);

                    service.salvar(doacao, fkTitular, 1);
                }
            }

        } catch (IOException e) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(201).build();
    }
}


