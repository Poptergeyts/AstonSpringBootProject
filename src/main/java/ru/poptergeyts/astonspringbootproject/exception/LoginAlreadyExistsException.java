package ru.poptergeyts.astonspringbootproject.exception;

import org.springframework.http.HttpStatus;

public class LoginAlreadyExistsException extends CustomException {
    public LoginAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "User with this login has already been signed up.");
    }
}
