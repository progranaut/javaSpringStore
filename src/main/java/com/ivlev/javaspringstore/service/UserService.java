package com.ivlev.javaspringstore.service;

import com.ivlev.javaspringstore.entity.Role;
import com.ivlev.javaspringstore.entity.User;
import com.ivlev.javaspringstore.model.RoleDto;
import com.ivlev.javaspringstore.model.UserAddDto;
import com.ivlev.javaspringstore.model.UserDto;
import com.ivlev.javaspringstore.model.UserNameAndRoleDto;
import com.ivlev.javaspringstore.repository.UserRepository;
import com.ivlev.javaspringstore.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    public ResponseEntity<?> addUser(UserAddDto userAddDto) {

        Set<Role> roles = roleService.getRoles(userAddDto.getRoles());

        System.out.println(roles);

        User user = User.builder()
                .id(UUID.fromString(userAddDto.getId()))
                .email(userAddDto.getEmail())
                //.phoneNumber(userDto.getPhoneNumber())
                //.address(userDto.getAddress())
                //.email(userDto.getEmail())
                .roles(roles)
                .build();

        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void changeUser(UserDto userDto){

        User user = getCurrentUser();
        user.setName(userDto.getName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());

        changeUser(user);

    }

    public void changeUser(User user) {
        userRepository.save(user);
    }

    public ResponseEntity<?> getCurrentUserNameAndRole() {

        User user = getCurrentUser();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Set<Role> roles = user.getRoles();
        Set<RoleDto> roleDtoSet = roles.stream()
                .map(role -> RoleDto.builder()
                .roleType(role.getRoleType().toString())
                .build())
                .collect(Collectors.toSet());

        return new ResponseEntity<>(UserNameAndRoleDto.builder()
                .name(user.getName())
                .roles(roleDtoSet)
                .build(), HttpStatus.OK);
    }

    public User getCurrentUser() {

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        System.out.println(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .isAuthenticated());

        if (principal == null) {
            return null;
        }

        if (!(principal instanceof AppUserDetails)) {
            return null;
        }

        System.out.println(principal);

        AppUserDetails userDetails = (AppUserDetails) principal;

        System.out.println(userDetails.getUsername());

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        return user;
    }

    public UserDto getCurrentUserDto() {

        User user = getCurrentUser();

        return toDto(user);

    }

    public UserDto toDto(User user) {

        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .email(user.getEmail())
                .build();

        return userDto;
    }

}
