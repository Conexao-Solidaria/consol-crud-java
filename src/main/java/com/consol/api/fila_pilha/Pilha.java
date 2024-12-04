package com.consol.api.fila_pilha;

import lombok.Data;

@Data
public class Pilha {
//    private Necessidade[] pilha;
    private int topo;

//    public Pilha(int capacidade) {
//        pilha = new Necessidade[capacidade];
//        topo = -1;
//    }
//
//    public Boolean isEmpty() {
//        return topo == -1;
//    }
//
//    public Boolean isFull() {
//        return topo == pilha.length - 1;
//    }
//
//    public void push(Necessidade info) {
//        if (isFull()) {
//            System.out.println("Pilha cheia!");
//        }
//        else {
//            pilha[++topo] = info;
//        }
//    }
//
//    public Necessidade pop() {
//        if (isEmpty()) {
//            return null;
//        }
//
//        return pilha[topo--];
//    }
//
//    public Necessidade peek() {
//        if (isEmpty()) {
//            return null;
//        }
//        return pilha[topo];
//    }
//
//    public void exibe() {
//        if (isEmpty()) {
//            System.out.println("Pilha vazia");
//        }
//        else {
//            for (int i = topo; i >= 0; i--) {
//                System.out.println(pilha[i]);
//            }
//        }
//    }
}
