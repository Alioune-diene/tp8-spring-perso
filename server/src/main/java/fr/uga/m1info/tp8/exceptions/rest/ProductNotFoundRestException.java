package fr.uga.m1info.tp8.exceptions.rest;

public class ProductNotFoundRestException extends RuntimeException {
    public ProductNotFoundRestException(String message) {
        super(message);
    }
}
