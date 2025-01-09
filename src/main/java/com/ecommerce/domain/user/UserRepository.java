package com.ecommerce.domain.user;

public interface UserRepository {
    Users findById(Long id);
    void save(Users user);
}