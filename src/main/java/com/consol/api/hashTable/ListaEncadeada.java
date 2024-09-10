package com.consol.api.hashTable;

import com.consol.api.dto.despesa.DespesaConsultaDto;
import com.consol.api.entity.Despesa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ListaEncadeada {
    Node head;

    public ListaEncadeada() {
        head = new Node(null);
    }

    public void insereNode(DespesaConsultaDto valor){
        Node node = new Node(valor);
        node.setNext(head);
        head = node;
    }

    public void exibe(){
        Node atual = head;

        while(atual.getNext() != null){
            System.out.println(atual.getInfo());
            atual = atual.getNext();
        }
    }

    public Node buscaNode(DespesaConsultaDto valor){
        Node atual = head;

        while(atual.getNext() != null){
            if(atual.getInfo() == valor){
                return atual;
            }
            atual = atual.getNext();
        }
        return null;
    }

    public DespesaConsultaDto[] buscaNode(Double valor){
        Node atual = head;
        DespesaConsultaDto[] listaNodes = new DespesaConsultaDto[getTamanho()];
        int i = 0;

        while(atual.getNext() != null){
            if(Objects.equals(atual.getInfo().getGasto(), valor)){
                listaNodes[i] = atual.getInfo();
                i += 1;
            }
            atual = atual.getNext();
        }
        System.out.println(Arrays.toString(listaNodes));
        return listaNodes;
    }

    public boolean removeNode(DespesaConsultaDto valor){
        Node ant = head;
        Node atual = head.getNext();

        while(atual.getNext() != null){
            if(atual.getInfo() == valor){
                ant.setNext(atual.getNext());
                return true;
            }
            ant = atual;
            atual = atual.getNext();
        }
        return false;
    }

    public int getTamanho(){
        Node atual = head;
        int tamanho = 0;

        while(atual.getNext() != null){
            tamanho += 1;
            atual = atual.getNext();
        }

        return tamanho;
    }

    public DespesaConsultaDto getElemento(int indice){
        Node atual = head;
        int tamanho = 0;

        while(atual.getNext() != null){
            if(tamanho == indice){
                return atual.getInfo();
            }
            tamanho += 1;
            atual = atual.getNext();
        }

        return null;
    }

    public boolean removeOcorrencias(DespesaConsultaDto valor){
        boolean ativador = true;
        int contador = 0;

        while (ativador) {

            ativador = removeNode(valor);
            contador += 1;
        }
        return contador >= 2;
    }

    public void arrayToList(DespesaConsultaDto[] vetor){
        if(getTamanho() == 1){
            System.out.println("Operação Invalida");
        }
        for (DespesaConsultaDto j : vetor) {
            insereNode(j);
        }
    }

    public DespesaConsultaDto[] listToArray(){
        Node atual = head;
        int contador = 0;

        DespesaConsultaDto[] despesaConsultaDto = new DespesaConsultaDto[getTamanho()];

        while(atual.getNext() != null){
            despesaConsultaDto[contador] = atual.getInfo();
            contador += 1;
            atual = atual.getNext();
        }

        return despesaConsultaDto;
    }
}
