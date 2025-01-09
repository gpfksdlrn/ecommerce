package com.ecommerce.infrastructure.user;

import com.ecommerce.domain.user.UserRepository;
import com.ecommerce.domain.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public Users findById(Long id) {
        return jpaRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 유저가 존재하지 않습니다."));
    }

    @Override
    public void save(Users user) {
        jpaRepository.save(user);
    }
}
