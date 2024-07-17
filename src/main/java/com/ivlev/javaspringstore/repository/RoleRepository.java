package com.ivlev.javaspringstore.repository;

import com.ivlev.javaspringstore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Set<Role> findAllByRoleTypeIn(Set<String> roles);

}
