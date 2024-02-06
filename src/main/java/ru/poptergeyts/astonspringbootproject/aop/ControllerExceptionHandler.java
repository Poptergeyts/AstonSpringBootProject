package ru.poptergeyts.astonspringbootproject.aop;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.poptergeyts.astonspringbootproject.exception.*;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler({
            LoginAlreadyExistsException.class,
            InvalidPasswordException.class,
            InvalidNewPasswordException.class,
            LoginDoesNotExist.class})
    public ResponseEntity<String> handleLoginAlreadyExistsException(CustomException customException) {
        return new ResponseEntity<>(customException.getMessage(), customException.getStatus());
    }
}
