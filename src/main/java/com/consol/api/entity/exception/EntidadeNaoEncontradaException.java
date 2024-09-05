package com.consol.api.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends RuntimeException{
    public EntidadeNaoEncontradaException(String entidade){super(String.format("%s não entrado(a)", entidade));}
}
