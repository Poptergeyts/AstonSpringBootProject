package ru.poptergeyts.astonspringbootproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException{
    @Getter
    private final HttpStatus status;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
