package com.example.websockets.repository;

import com.example.websockets.role.Role;
import com.example.websockets.role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName role);
}
