package com.ivlev.javaspringstore.service;

import com.ivlev.javaspringstore.entity.Role;
import com.ivlev.javaspringstore.entity.User;
import com.ivlev.javaspringstore.enums.RoleType;
import com.ivlev.javaspringstore.model.UserRegisterDto;
import com.ivlev.javaspringstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    public ResponseEntity<?> addUser(UserRegisterDto userRegisterDto) {

        Set<Role> roles = roleService.getRoles(userRegisterDto.getRoles());

        System.out.println(roles);

        User user = User.builder()
                .id(UUID.fromString(userRegisterDto.getId()))
                .email(userRegisterDto.getEmail())
                //.phoneNumber(userDto.getPhoneNumber())
                //.address(userDto.getAddress())
                //.email(userDto.getEmail())
                .roles(roles)
                .build();

        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
