package com.ivlev.javaspringstore.service;

import com.ivlev.javaspringstore.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ivlev.javaspringstore.entity.Role;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Set<Role> getRoles(Set<String> roles) {

        return roleRepository.findAllByRoleTypeIn(roles);

    }

}
