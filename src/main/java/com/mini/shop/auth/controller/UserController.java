package com.mini.shop.auth.controller;

import com.mini.shop.auth.dto.UserDto;
import com.mini.shop.auth.service.UserService;
import com.mini.shop.error.exception.DuplicatedUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserDto userDto) throws DuplicatedUserException {
        return new ResponseEntity<>(userService.signUp(userDto), HttpStatus.OK);
    }

    @PostMapping("/find-pwd")
    public ResponseEntity<?> findPwd(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.findPwd(userDto), HttpStatus.OK);
    }
}
