package com.github.RafaelScarpelli.backend.exception;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.github.RafaelScarpelli.backend.dto.RespostaErro;

@RestControllerAdvice
public class ExececaoGlobal {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<RespostaErro> business(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage()).collect(Collectors.toList());

        RespostaErro respostaErro = new RespostaErro(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Negócio",
                "Campos Inválidos", request.getDescription(false), erros);

        return new ResponseEntity<>(respostaErro, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespostaErro> validacao(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage()).collect(Collectors.toList());

        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST.value(), "Erro de Validação",
                "Campos Inválidos", request.getDescription(false), erros);

        return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RespostaErro> notFound(NotFoundException ex, WebRequest request) {

        RespostaErro respostaErro = new RespostaErro(HttpStatus.NOT_FOUND.value(), "Não encontrado", ex.getMessage(),
                request.getDescription(false), null);
        return new ResponseEntity<>(respostaErro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaErro> global(Exception ex, WebRequest request) {

        RespostaErro respostaErro = new RespostaErro(HttpStatus.BAD_REQUEST.value(), "Erro Interno", ex.getMessage(),
                request.getDescription(false), null);
        return new ResponseEntity<>(respostaErro, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
