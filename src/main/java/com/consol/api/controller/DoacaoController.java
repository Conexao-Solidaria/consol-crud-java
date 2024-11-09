package com.consol.api.controller;

import com.consol.api.dto.doacao.*;
import com.consol.api.entity.Doacao;
import com.consol.api.entity.Instituicao;
import com.consol.api.entity.Titular;
import com.consol.api.service.DoacaoService;
import com.consol.api.service.InstituicaoService;
import com.consol.api.service.TitularService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/titular/{id}/instituicao/{idInstituicao}") // idInstituicao será sempre 1
    private ResponseEntity<DoacaoConsultaDto> criar(
            @RequestBody @Valid DoacaoCadastroDto dto,
            @PathVariable int id,
            @PathVariable int idInstituicao

    ) {

        Doacao doacao = DoacaoMapper.toEntity(dto);
        Doacao doacaoSalva = service.salvar(doacao,id,idInstituicao);

        DoacaoConsultaDto doacaoConsultaDto = DoacaoMapper.toDto(doacaoSalva);

        URI uri = URI.create("/doacoes/" + doacaoConsultaDto.getId());

        return ResponseEntity.created(uri).body(doacaoConsultaDto);
    }

    @GetMapping
    private ResponseEntity<List<DoacaoConsultaDto>> listar() {
        List<Doacao> doacoes = service.listar();

        if (doacoes.isEmpty()) return ResponseEntity.status(204).build();

        List<DoacaoConsultaDto> dtos = DoacaoMapper.toDto(doacoes);

        return ResponseEntity.status(200).body(dtos);
    }

    @GetMapping("/{id}")
    private ResponseEntity<DoacaoConsultaDto> porId(
            @PathVariable Integer id
    ) {
        Doacao doacao = service.listarPorId(id);
        DoacaoConsultaDto dto = DoacaoMapper.toDto(doacao);

        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/filtro/por-data")
    private ResponseEntity<List<DoacaoConsultaDto>> porData(
            @RequestParam LocalDate data
    ) {
        List<Doacao> doacoes = service.listarPorData(data);

        if (doacoes.isEmpty()) return ResponseEntity.noContent().build();

        List<DoacaoConsultaDto> dtos = DoacaoMapper.toDto(doacoes);

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/filtro/por-periodo")
    private ResponseEntity<List<DoacaoConsultaDto>> porPeriodo(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim
    ) {
        List<Doacao> doacoes = service.listarPorPeriodo(dataInicio, dataFim);

        if (doacoes.isEmpty()) return ResponseEntity.noContent().build();

        List<DoacaoConsultaDto> dtos = DoacaoMapper.toDto(doacoes);

        return ResponseEntity.ok(dtos);
    }


    @PutMapping("/atualizar-flag/{id}")
    public ResponseEntity<DoacaoConsultaDto> atualizarFlag(
            @RequestBody @Valid DoacaoAtualizarFlagDto dto,
            @PathVariable Integer id
    ){
        Doacao doacao = DoacaoMapper.toEntity(dto);
        Doacao doacaoAtualizada = service.atualizarFlag(id, doacao);
        DoacaoConsultaDto doacaoConsultaDto = DoacaoMapper.toDto(doacaoAtualizada);

        return ResponseEntity.ok(doacaoConsultaDto);
    }

    @PutMapping("/atualizar-descricao/{id}")
    public ResponseEntity<DoacaoConsultaDto> atualizarDescricao(
            @RequestBody @Valid DoacaoAtualizarDescricaoDto dto,
            @PathVariable Integer id
    ){
        Doacao doacao = DoacaoMapper.toEntity(dto);
        Doacao doacaoAtualizada = service.atualizarDescricao(id,doacao);
        DoacaoConsultaDto doacaoConsultaDto = DoacaoMapper.toDto(doacaoAtualizada);

        return ResponseEntity.status(200).body(doacaoConsultaDto);

    }


    @PutMapping("/confirmar-doacao/{id}")
    public ResponseEntity<DoacaoConsultaDto> confirmarDoacao(
            @RequestBody @Valid DoacaoConfirmacaoDto dto,
            @PathVariable Integer id
    ){
        Doacao doacao = DoacaoMapper.toEntity(dto);
        Doacao doacaoAtualizada = service.confirmarDoacao(id, doacao);
        DoacaoConsultaDto doacaoConsultaDto = DoacaoMapper.toDto(doacaoAtualizada);

        return ResponseEntity.status(200).body(doacaoConsultaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/por-nome")
    public ResponseEntity<List<DoacaoConsultaDto>> porNome(@RequestParam String nome){
        List<Doacao> doacaos = service.listarPorNome(nome);

        if (doacaos.isEmpty()) return ResponseEntity.status(204).build();

        List<DoacaoConsultaDto> dto = DoacaoMapper.toDto(doacaos);
        return ResponseEntity.status(200).body(dto);
    }

    @GetMapping("/baixar-csv")
    public ResponseEntity<byte[]> baixarCsv() throws IOException{
        List<Doacao> doacoes = service.listar();

        if (doacoes.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        List<String[]> data = converterCsv(doacoes);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try(OutputStreamWriter writer = new OutputStreamWriter(byteArrayOutputStream,StandardCharsets.UTF_8)){
            for (String[] row : data){
                writer.write(String.join(",",row));
                writer.write("\n");
            }
        }

        byte[] csvBytes = byteArrayOutputStream.toByteArray();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"dados.csv\"")
                .header(HttpHeaders.CONTENT_TYPE, "text/csv")
                .body(csvBytes);
    }

    private static List<String[]> converterCsv(List<Doacao> doacaos) {
        List<String[]> data = new ArrayList<>();

        data.add(new String[]{"ID", "Descrição", "Data da Doação","Entregue?","Titular"});

        for (Doacao doacao : doacaos) {

            String[] row = new String[5];
            row[0] = String.valueOf(doacao.getId());
            row[1] = doacao.getDescricao();
            row[2] = doacao.getDataDoacao().toString();
            row[3] = doacao.getFlagDoacaoEntregue() == 1 ? "Sim" : "Não";
            row[4] = doacao.getTitular() != null ? doacao.getTitular().getNome() : "Sem Titular";
            data.add(row);
        }

        return data;
    }

    @GetMapping("/baixar-txt")
    public ResponseEntity<byte[]> baixarTxt() throws IOException {
        List<Doacao> doacoes = service.listar();

        if (doacoes.isEmpty()) return ResponseEntity.status(204).build();

        List<String[]> data = converterTxt(doacoes);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8)) {

            writer.write("00DOACAO");
            writer.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
            writer.write("01");
            writer.write("\n");

            Integer qtdDoacao = 0;

            for (String[] row : data) {
                String linha = String.format("%-50.50s", row[0]) +
                        String.format("%-5.5s", row[1]) +
                        String.format("%-19.19s", row[2]) +
                        String.format("%-1.1s", row[3]) +
                        String.format("%2.2s", row[4]);
                writer.write(linha);
                writer.write("\n");

                qtdDoacao++;
            }

            writer.write(String.format("%-2.2s","01"));
            writer.write(String.format("%-5.5s",String.valueOf(qtdDoacao)));
        }

        byte[] txtBytes = byteArrayOutputStream.toByteArray();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"dados.txt\"")
                .header(HttpHeaders.CONTENT_TYPE, "text/plain")
                .body(txtBytes);
    }


    private static List<String[]> converterTxt(List<Doacao> doacaos) {
        Integer qtdRegistro = 0;

        List<String[]> result = new ArrayList<>();

        for (Doacao doacao : doacaos) {
            String[] linha = new String[5];

            linha[0] = String.format("%-50.50s", doacao.getDescricao());
            linha[1] = String.format("%-5.5s", doacao.getId());
            linha[2] = String.format("%-19.19s", doacao.getDataDoacao().toString());
            linha[3] = String.format("%-1.1s", doacao.getFlagDoacaoEntregue() == 1 ? "S" : "N");
            linha[4] = String.format("%2.2s", doacao.getInstituicao().getId());
            result.add(linha);

            qtdRegistro++;
        }

        return result;
    }


    public static void gravaRegistro(String nomeArq, String registro){
        BufferedWriter saida = null;

        try{
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro){
            System.out.println("ERRO AO ABRIR ARQUIVO" + erro);
        }

        try{
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro){
            System.out.println("ERRO NA GRAVAÇÃO DO ARQUIVO");
        }
    }
    public static void gravaArquivoTxt(List<Doacao> doacoes, String nomeArq) {
        nomeArq += ".txt";

        int contaRegistro = 0;
        String header = "00DOACAO";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        header += "01";
        gravaRegistro(nomeArq, header);

        String corpo;
        for (Doacao a: doacoes) {
            corpo = "02";
            corpo += String.format("%-50.50s", a.getDescricao());
            corpo += String.format("%-5.5s", a.getId());
            corpo += String.format("%-8.8s", a.getFlagDoacaoEntregue());
            corpo += String.format("%5.2s", a.getDataDoacao());
            corpo += String.format("%-1.1s",a.getFlagDoacaoEntregue());
            corpo += String.format("%2.2s", a.getInstituicao().getId());

            gravaRegistro(nomeArq, corpo);
            contaRegistro++;
        }

        String trailer = "01";
        trailer += String.format("%010d", contaRegistro);
        gravaRegistro(nomeArq, trailer);
    }

    public static void lerArquivoCsv(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        }
        catch (FileNotFoundException erro){
            System.out.println("ARQUIVO INEXISTENTE");
            System.exit(1);
        }

        try {
            System.out.printf("%4S %-15S %-9S %4S\n",
                    "id", "nome", "porte", "peso");
            while (entrada.hasNext()){
                int id = entrada.nextInt();
                String nome = entrada.next();
                String porte = entrada.next();
                Double peso = entrada.nextDouble();

                System.out.printf("%4d %-15s %-9s %4.1f\n", id, nome, porte, peso);
            }
        }
        catch (NoSuchElementException erro){
            System.out.println("ARQUIVO COM PROBLEMAS");
            erro.printStackTrace();
            deuRuim = true;
        }

        catch (IllegalStateException erro){
            System.out.println("ERRO NA LEITURA DO ARQUIVO");
            erro.printStackTrace();
            deuRuim = true;
        }
        finally {
            if (entrada != null) {
                entrada.close();
            }
            try {
                if (arq != null) {
                    arq.close();
                }
            } catch (IOException erro) {
                System.out.println("ERRO AO FECHAR O ARQUIVO");
                deuRuim = true;
            }
        }
    }

    public static void lerArquivoTxt(String nomeArq, InstituicaoService instituicaoService, TitularService titularService) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String descricao;
        int titularId;
        int instituicaoId;
        Byte flagDoacaoEntregue;
        Integer id;
        LocalDateTime dataDoacao;
        int contaRegistroDados = 0;
        int qtdRegistroGravado;

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("ERRO AO LER ARQUIVO: " + erro.getMessage());
            return; // Adiciona um return para evitar continuar caso ocorra erro na leitura do arquivo
        }

        try {
            registro = entrada.readLine();
            while (registro != null) {
                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("00")) {
                    System.out.println("É UM REGISTRO DE HEADER");
                    System.out.println("TIPO DE ARQUIVO: " + registro.substring(2, 6));
                    System.out.println("SEMESTRE: " + registro.substring(6, 11));
                    System.out.println("DATA E HORA DA GRAVAÇÃO DO ARQUIVO: " + registro.substring(11, 30));
                    System.out.println("VERSÃO DO DOCUMENTO DE LAYOUT: " + registro.substring(30, 32));
                } else if (tipoRegistro.equals("01")) {
                    System.out.println("É UM REGISTRO DE TRAILER");
                    qtdRegistroGravado = Integer.parseInt(registro.substring(2, 12));

                    if (contaRegistroDados == qtdRegistroGravado) {
                        System.out.println("QTD DE REG GRAVADOS COMPÁTIVEL COM A QTD REG LINHAS");
                    } else {
                        System.out.println("INCONSISTENCIA NO REGISTRO DOS DADOS");
                    }
                } else if (tipoRegistro.equals("02")) {
                    System.out.println("É UM REGISTRO DE DADOS");
                    id = Integer.parseInt(registro.substring(2, 5).trim());
                    descricao = registro.substring(10, 65).trim();
//                    statusDoacao = Byte.parseByte(registro.substring(5, 10).trim()); ---- REMOVER - EDU
                    dataDoacao = LocalDateTime.parse(registro.substring(65, 75).trim());
                    flagDoacaoEntregue = Byte.parseByte(registro.substring(75, 80).trim());

                    instituicaoId = Integer.parseInt(registro.substring(80, 85).trim());
                    titularId = Integer.parseInt(registro.substring(85, 100).trim());

                    // Obtenha as instâncias de Instituicao e Titular usando os IDs
                    Instituicao instituicao = instituicaoService.consultarPorId(instituicaoId);
                    Titular titular = titularService.porId(titularId);

                    contaRegistroDados++;
                    // Agora instanciamos Doacao com as entidades corretas
                    Doacao doacoes = new Doacao(id, descricao, dataDoacao, flagDoacaoEntregue, instituicao, titular);
                    System.out.println("DOACAO ADICIONADA: " + doacoes);
                } else {
                    System.out.println("É UM REGISTRO INVÁLIDO");
                }
                registro = entrada.readLine();
            }
        } catch (IOException erro) {
            System.out.println("ERRO AO LER ARQUIVO: " + erro.getMessage());
            erro.printStackTrace();
        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {
                    System.out.println("ERRO AO FECHAR O ARQUIVO: " + e.getMessage());
                }
            }
        }
    }
}
