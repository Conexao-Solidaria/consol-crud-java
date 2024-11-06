package com.consol.api.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ProibidoException extends Exception{
    public ProibidoException(String entidade){super(String.format("%s Proíbido", entidade));}
}
