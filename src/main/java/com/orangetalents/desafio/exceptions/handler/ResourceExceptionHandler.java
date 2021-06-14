package com.orangetalents.desafio.exceptions.handler;

import com.fasterxml.jackson.core.JsonParseException;
import com.orangetalents.desafio.exceptions.NaoAutorizadoException;
import com.orangetalents.desafio.exceptions.NaoEncontradoException;
import com.orangetalents.desafio.exceptions.UsuarioCadastradoException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadrao> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ErroValidacao err = new ErroValidacao(System.currentTimeMillis(), "Erro de validação", "Informe os campos faltantes para concluir a solicitação", request.getRequestURI());

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            err.addErro(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErroPadrao> jsonParseException(JsonParseException e, HttpServletRequest request) {
        ErroPadrao err = new ErroPadrao(System.currentTimeMillis(), "Formato de entrada inválido", e.getLocalizedMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(UsuarioCadastradoException.class)
    public ResponseEntity<ErroPadrao> usuarioCadastradoException(UsuarioCadastradoException e, HttpServletRequest request) {
        ErroPadrao err = new ErroPadrao(System.currentTimeMillis(), "Informações repetidas", e.getLocalizedMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroPadrao> illegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        ErroPadrao err = new ErroPadrao(System.currentTimeMillis(), "Formato de entrada inválido", e.getLocalizedMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ErroPadrao> propertyReferenceException(PropertyReferenceException e, HttpServletRequest request) {
        ErroPadrao err = new ErroPadrao(System.currentTimeMillis(), "Nenhum Resultado encontrado para o filtro passado", e.getLocalizedMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErroPadrao> naoEncontradoException(NaoEncontradoException e, HttpServletRequest request) {
        ErroPadrao err = new ErroPadrao(System.currentTimeMillis(), "Não Encontrado", e.getLocalizedMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(NaoAutorizadoException.class)
    public ResponseEntity<ErroPadrao> naoAutorizadoException(NaoAutorizadoException e, HttpServletRequest request) {
        ErroPadrao err = new ErroPadrao(System.currentTimeMillis(), "Sem Autoriização", e.getLocalizedMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
}
