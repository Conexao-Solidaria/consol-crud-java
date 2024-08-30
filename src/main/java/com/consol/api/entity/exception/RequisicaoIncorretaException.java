package com.consol.api.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequisicaoIncorretaException extends RuntimeException{
    public RequisicaoIncorretaException(String entidade){super(String.format("%s Requisição incorreta", entidade));}
}
