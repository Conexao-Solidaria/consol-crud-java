package com.consol.api.service;

import com.consol.api.entity.Doacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoacaoRelatoriosService {
    public List <String[]> converterCsv(List<Doacao> doacaos) {
        List<String[]> data = new ArrayList <>();

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

    public List<String[]> converterTxt(List<Doacao> doacaos) {
        Integer qtdRegistro = 0;

        List<String[]> result = new ArrayList<>();

        for (Doacao doacao : doacaos) {
            String[] linha = new String[6];

            linha[0] = String.format("%-5.5s", doacao.getId());
            linha[1] = String.format("%-200.200s", doacao.getDescricao());
            linha[2] = String.format("%-19.19s", doacao.getDataDoacao().toString());
            linha[3] = String.format("%-1.1s", doacao.getFlagDoacaoEntregue() == 1 ? "S" : "N");
            linha[4] = String.format("%5.5s", doacao.getInstituicao().getId());
            linha[5] = String.format("%5.5s",doacao.getTitular().getId());
            result.add(linha);

            qtdRegistro++;
        }

        return result;
    }




}
