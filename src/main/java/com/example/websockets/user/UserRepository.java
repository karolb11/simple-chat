package com.example.websockets.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<UserDTO> findUserDTOById(Long id);

    Optional<UserDTO> findUserDTOByUsername(String username);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);
}
