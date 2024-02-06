package ru.poptergeyts.astonspringbootproject.exception;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends CustomException{
    public InvalidPasswordException() {
        super(HttpStatus.UNAUTHORIZED, "Invalid password.");
    }
}
