package com.example.websockets.repository;

import com.example.websockets.model.Role;
import com.example.websockets.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName role);
}
