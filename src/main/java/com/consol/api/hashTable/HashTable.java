package com.consol.api.hashTable;

import com.consol.api.dto.despesa.DespesaConsultaDto;
import com.consol.api.entity.Despesa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HashTable {
    ListaEncadeada[] listaEncadeadas;

    public HashTable(int tamanhoHash) {
        listaEncadeadas = new ListaEncadeada[tamanhoHash];
        for (int i = 0; i < tamanhoHash; i++) {
            listaEncadeadas[i] = new ListaEncadeada();
        }
    }

    public void insere(DespesaConsultaDto valor){
        listaEncadeadas[funcaoHash(valor)].insereNode(valor);
    }

    public int funcaoHash(DespesaConsultaDto valor){
        return (int) (valor.getGasto()%listaEncadeadas.length);
    }

    public int funcaoHash(Double valor) {
        return (int) (valor%listaEncadeadas.length);
    }

    public List<DespesaConsultaDto> pesquisaValor(Double valor){
        return List.of(listaEncadeadas[funcaoHash(valor)].buscaNode(valor));
    }
}
