package com.consol.api.hashTable;

import com.consol.api.dto.despesa.DespesaConsultaDto;
import com.consol.api.entity.Despesa;

public class Node {
    private DespesaConsultaDto info;

    private Node next;

    public Node(DespesaConsultaDto info) {
        this.info = info;
    }

    public DespesaConsultaDto getInfo() {
        return info;
    }

    public void setInfo(DespesaConsultaDto info) {
        this.info = info;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "info=" + info +
                ", next=" + next +
                '}';
    }
}
