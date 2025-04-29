package com.engcode.agendador_tarefas.infrastructure.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {

    public UnauthorizedException(String menssagem) {

        super(menssagem);
    }

    public UnauthorizedException(String menssagem, Throwable throwable) {

        super(menssagem, throwable);
    }
}
