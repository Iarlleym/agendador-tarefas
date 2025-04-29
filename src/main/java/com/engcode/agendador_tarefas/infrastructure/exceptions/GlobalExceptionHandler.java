package com.engcode.agendador_tarefas.infrastructure.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Anotação que faz que tudo que esteja aki seja aplicado as demais controllers do micro serviço.
@ControllerAdvice
public class GlobalExceptionHandler {

    //Anotação para dizer que é uma exceção global.
    @ExceptionHandler (ResourceNotFoundException.class)
    public ResponseEntity<String> handlerResourceNotFoundException (ResourceNotFoundException resourceNotFoundException) {
        return new ResponseEntity<>(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Anotação para dizer que é uma exceção global.
    @ExceptionHandler (UnauthorizedException.class)
    public ResponseEntity<String> handlerUnauthorizedException (UnauthorizedException unauthorizedException) {
        return new ResponseEntity<>(unauthorizedException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
