package com.ecommerce.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Users getUser(Long userId) {
        return userRepository.findById(userId);
    }
}
