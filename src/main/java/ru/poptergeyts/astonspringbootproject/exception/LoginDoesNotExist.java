package ru.poptergeyts.astonspringbootproject.exception;

import org.springframework.http.HttpStatus;

public class LoginDoesNotExist extends CustomException{
    public LoginDoesNotExist() {
        super(HttpStatus.NOT_FOUND, "User with this login is not registered.");
    }
}
