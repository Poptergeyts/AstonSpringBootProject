package ru.poptergeyts.astonspringbootproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.poptergeyts.astonspringbootproject.applicationService.UserService;
import ru.poptergeyts.astonspringbootproject.dto.ChangePasswordDto;
import ru.poptergeyts.astonspringbootproject.dto.UserDto;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
    Метод, отлавливающий get запрос по url: /signUp c параметром login и password
     */
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signUp(userDto));
    }

    /*
    Метод, отлавливающий get запрос по url: /signIn c параметром login и password
     */
    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signIn(userDto));
    }

    /*
    Метод, отлавливающий get запрос по url: /changePassword c параметром login, oldPassword и newPassword
     */
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        return ResponseEntity.ok(userService.changePassword(changePasswordDto));
    }

    /*
    Метод, отлавливающий get запрос по url: /changePassword без параметров
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }
}
