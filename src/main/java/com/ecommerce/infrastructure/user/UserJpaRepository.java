package com.ecommerce.infrastructure.user;

import com.ecommerce.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<Users, Long> {
    Optional<Users> findById(Long id);
}