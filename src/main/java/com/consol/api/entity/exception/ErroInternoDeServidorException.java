package com.consol.api.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErroInternoDeServidorException extends Exception{
    public ErroInternoDeServidorException(String entidade){super(String.format("%s Erro interno de servidor", entidade));}
}
