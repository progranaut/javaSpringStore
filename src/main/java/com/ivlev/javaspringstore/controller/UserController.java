package com.ivlev.javaspringstore.controller;

import com.ivlev.javaspringstore.model.UserAddDto;
import com.ivlev.javaspringstore.model.UserDto;
import com.ivlev.javaspringstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/add-user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addUser(@RequestBody UserAddDto userAddDto){
        System.out.println(userAddDto);
        return userService.addUser(userAddDto);
    }

    @PostMapping("/change")
    public void changeUser(@RequestBody UserDto userDto) {
        userService.changeUser(userDto);
    }

}
