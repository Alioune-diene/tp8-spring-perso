package fr.uga.m1info.tp8.exceptions.rest;

public class CommandNotFoundRestException extends RuntimeException {
    public CommandNotFoundRestException(String message) {
        super(message);
    }
}
