package fr.uga.m1info.tp8.exceptions.rest;

public class ClientNotFoundRestException extends RuntimeException {
    public ClientNotFoundRestException(String message) {
        super(message);
    }
}
