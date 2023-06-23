package com.mini.shop.auth.controller;

import com.mini.shop.auth.dto.UserDto;
import com.mini.shop.auth.service.UserService;
import com.mini.shop.error.exception.DuplicatedUserException;
import com.mini.shop.error.exception.NotFoundUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<?> insertSignUp(@Valid @RequestBody UserDto userDto) throws DuplicatedUserException {
        return new ResponseEntity<>(userService.insertSignUp(userDto), HttpStatus.OK);
    }

    @PostMapping("/find-pwd")
    public ResponseEntity<?> getFindPwd(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.getFindPwd(userDto), HttpStatus.OK);
    }

    @GetMapping("/info")
    public ResponseEntity<?> getMemberInfo(HttpServletRequest request, HttpServletResponse response) throws NotFoundUserException {
        return new ResponseEntity<>(userService.getMemberInfo(request.getAttribute("id").toString()), HttpStatus.OK);
    }

    @PutMapping("/info")
    public ResponseEntity<?> updateMemberInfo(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updateMemberInfo(userDto), HttpStatus.OK);
    }
}
