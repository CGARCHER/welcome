package com.cipri.welcome.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class FieldErrorResponse extends ErrorResponse{

    private Map<String, String> errors;

    public FieldErrorResponse(String message, String details, int errorcode,Map<String, String> errors) {
        super(message, details, errorcode);
        this.errors = errors;
    }
}
