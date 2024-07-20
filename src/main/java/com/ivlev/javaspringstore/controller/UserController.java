package com.ivlev.javaspringstore.controller;

import com.ivlev.javaspringstore.model.UserAddDto;
import com.ivlev.javaspringstore.model.UserDto;
import com.ivlev.javaspringstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

}
