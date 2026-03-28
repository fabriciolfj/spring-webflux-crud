package com.github.fabriciolfj.crud.product.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(final String msg) {
        super(msg);
    }
}
