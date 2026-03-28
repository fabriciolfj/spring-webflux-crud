package com.github.fabriciolfj.crud.product.handlers;

import com.github.fabriciolfj.crud.product.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ErrorDTO> productNotFoundException(final ProductNotFoundException ex) {
        return Mono.just(new ErrorDTO(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ErrorDTO> exception(final Exception ex) {
        return Mono.just(new ErrorDTO(ex.getMessage()));
    }
}
