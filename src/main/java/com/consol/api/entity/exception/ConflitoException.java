package com.consol.api.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflitoException extends RuntimeException{
    public ConflitoException(String entidade){super(String.format("%s Em conflito", entidade));}
}
