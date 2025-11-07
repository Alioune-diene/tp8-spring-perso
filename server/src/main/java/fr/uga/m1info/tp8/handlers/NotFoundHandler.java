package fr.uga.m1info.tp8.handlers;

import fr.uga.m1info.tp8.errors.ErrorResponse;
import fr.uga.m1info.tp8.errors.ErrorType;
import fr.uga.m1info.tp8.exceptions.rest.ClientNotFoundRestException;
import fr.uga.m1info.tp8.exceptions.rest.CommandNotFoundRestException;
import fr.uga.m1info.tp8.exceptions.rest.ProductNotFoundRestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundHandler {

    @ExceptionHandler(ClientNotFoundRestException.class)
    public ResponseEntity<ErrorResponse> handleClientNotFoundRestException(ClientNotFoundRestException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse
                        .builder()
                        .type(ErrorType.CLIENT_NOT_FOUND)
                        .error(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(CommandNotFoundRestException.class)
    public ResponseEntity<ErrorResponse> handleCommandNotFoundRestException(CommandNotFoundRestException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse
                        .builder()
                        .type(ErrorType.COMMAND_NOT_FOUND)
                        .error(ex.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(ProductNotFoundRestException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundRestException(ProductNotFoundRestException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse
                        .builder()
                        .type(ErrorType.PRODUCT_NOT_FOUND)
                        .error(ex.getMessage())
                        .build()
                );
    }
}
