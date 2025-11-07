package fr.uga.m1info.tp8.handlers;

import fr.uga.m1info.tp8.errors.ErrorResponse;
import fr.uga.m1info.tp8.errors.ErrorType;
import fr.uga.m1info.tp8.exceptions.rest.EmailInvalidFormatRestException;
import fr.uga.m1info.tp8.exceptions.rest.EmptyCommandRestException;
import fr.uga.m1info.tp8.exceptions.rest.ProductNotInCommandRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadRequestHandler {

    @ExceptionHandler(EmptyCommandRestException.class)
    public ResponseEntity<ErrorResponse> handleEmptyCommandRestException(EmptyCommandRestException e) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse
                        .builder()
                        .type(ErrorType.CREATION_EMPTY_COMMAND)
                        .error(e.getMessage())
                        .build());
    }

    @ExceptionHandler(EmailInvalidFormatRestException.class)
    public ResponseEntity<ErrorResponse> handleEmailInvalidFormatRestException(EmailInvalidFormatRestException e) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse
                        .builder()
                        .type(ErrorType.EMAIL_INVALID_EXCEPTION)
                        .error(e.getMessage())
                        .build());
    }

    @ExceptionHandler(ProductNotInCommandRestException.class)
    public ResponseEntity<ErrorResponse> handleProductNotInCommandRestException(ProductNotInCommandRestException e) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse
                        .builder()
                        .type(ErrorType.PRODUCT_NOT_IN_COMMAND)
                        .error(e.getMessage())
                        .build());
    }
}
