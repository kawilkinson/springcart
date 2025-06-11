package com.kwilkinson.springcart.repositories;

import com.kwilkinson.springcart.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
