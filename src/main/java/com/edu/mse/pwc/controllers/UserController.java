package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.UserDto;
import com.edu.mse.pwc.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto user) {
        return userService.createUser(user);
    }
}
