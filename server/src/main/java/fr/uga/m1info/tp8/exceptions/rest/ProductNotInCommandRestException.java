package fr.uga.m1info.tp8.exceptions.rest;

public class ProductNotInCommandRestException extends RuntimeException {
    public ProductNotInCommandRestException(String message) {
        super(message);
    }
}
