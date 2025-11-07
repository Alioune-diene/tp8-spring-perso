package fr.uga.m1info.tp8.exceptions.rest;

public class EmailInvalidFormatRestException extends RuntimeException {
    public EmailInvalidFormatRestException(String message) {
        super(message);
    }
}
