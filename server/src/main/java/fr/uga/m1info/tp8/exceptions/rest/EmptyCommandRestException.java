package fr.uga.m1info.tp8.exceptions.rest;

public class EmptyCommandRestException extends RuntimeException {
    public EmptyCommandRestException(String message) {
        super(message);
    }
}
