package org.example.Exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String massage) {
        super(massage);
    }
}

