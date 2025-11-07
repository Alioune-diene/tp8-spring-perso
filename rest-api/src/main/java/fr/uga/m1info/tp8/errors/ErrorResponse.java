package fr.uga.m1info.tp8.errors;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    private ErrorType type;
    private String error;
}
