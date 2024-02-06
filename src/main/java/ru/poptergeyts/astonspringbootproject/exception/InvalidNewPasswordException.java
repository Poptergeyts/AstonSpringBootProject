package ru.poptergeyts.astonspringbootproject.exception;

import org.springframework.http.HttpStatus;

public class InvalidNewPasswordException extends CustomException{
    public InvalidNewPasswordException() {
        super(HttpStatus.BAD_REQUEST, "Old password and new password are the same.");
    }
}
