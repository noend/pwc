package com.edu.mse.pwc.controllers;

import com.edu.mse.pwc.dtos.UserDto;
import com.edu.mse.pwc.exceptions.UsernameFoundException;
import com.edu.mse.pwc.mappers.UserMapper;
import com.edu.mse.pwc.persistence.entities.Role;
import com.edu.mse.pwc.persistence.entities.UserEntity;
import com.edu.mse.pwc.services.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    private UserDto user;

    @PostMapping("/create")
    @ApiResponses({@ApiResponse(code = 200, message = "ok")})
    @ExceptionHandler({UsernameFoundException.class})
    public UserDto createUser(@RequestBody UserDto user) throws Exception {

        if (userExist(user.getUsername())) {
            return user;
        }

        user.setRole(Role.USER);
        return userService.createUser(user);

    }

    @PostMapping("/getUser")
    @ResponseBody
    public UserDto getUser(@RequestParam("name") String name) {
        UserEntity entity = userService.getUserByUsername(name);

        if (entity != null) {

            return userMapper.userEntityToDto((entity));
        } else {
            return null;
        }

    }

    private boolean userExist(String name) {
        return userService.getUserByUsername(name) != null;
    }
}

