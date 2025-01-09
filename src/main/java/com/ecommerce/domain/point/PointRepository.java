package com.ecommerce.domain.point;

public interface PointRepository {
    Point findByUserId(Long userId);
    void save(Point point);
}