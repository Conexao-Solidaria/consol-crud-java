package com.consol.api.fila_pilha;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilaCircular {
    int tamanho, inicio, fim;
    int[] fila;


    public FilaCircular(int capacidade) {
        fila = new int[capacidade];
        tamanho = 0;
        inicio = 0;
        fim = 0;
    }


    public boolean isEmpty() {
        return tamanho == 0;
    }


    public boolean isFull() {
        return tamanho == fila.length;
    }


    public void insert(int info) {
        if (isFull()) {
            throw new IllegalStateException("Fila cheia");
        }
        else {
            fila[fim] = info;
            fim = (fim + 1) % fila.length;
            tamanho++;
        }
    }


    public int peek() {
        return fila[inicio];
    }


    public int poll() {
        int primeiro = peek();
        if (!isEmpty()) {
            fila[inicio] = 0;   // limpa o elemento removido
            inicio = (inicio+1) % fila.length;
            tamanho--;
        }
        return primeiro;
    }


    public void exibe() {
        if (isEmpty()) {
            System.out.println("Fila vazia");
        }
        else {
            for (int ind=inicio, cont=0; cont<tamanho; ind=(ind+1)%fila.length,cont++) {
                System.out.println(fila[ind]);
            }
        }
    }


    public int[] getFila(){
        int[] vetor = new int[tamanho];

        for (int ind=inicio, cont=0; cont<tamanho; ind=(ind+1)%fila.length,cont++) {
            vetor[cont] = fila[ind];
        }

        return vetor;
    }

}
