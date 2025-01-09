package com.ecommerce.infrastructure.point;

import com.ecommerce.domain.point.Point;
import com.ecommerce.domain.point.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointRepositoryImpl implements PointRepository {

    private final PointJpaRepository jpaRepository;

    @Override
    public Point findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId)
                .orElseThrow(() -> new NullPointerException("해당 유저의 포인트가 존재하지 않습니다."));
    }

    @Override
    public void save(Point point) {
        jpaRepository.save(point);
    }
}