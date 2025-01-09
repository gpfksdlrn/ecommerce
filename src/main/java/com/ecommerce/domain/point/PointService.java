package com.ecommerce.domain.point;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointService {
private final PointRepository pointRepository;
    private static final int MAX_CHARGE_POINT = 10000000; // 최대 충전 금액 (1000만원)
    private static final int MIN_CHARGE_POINT = 10; // 최소 충전 금액 (10원)

    @Transactional(readOnly = true)
    public Point getUserPoint(Long userId) {
        return pointRepository.findByUserId(userId);
    }

    public Point chargeUserPoint(Long userId, Long chargePoint) {
        if (chargePoint > MAX_CHARGE_POINT) {
            throw new IllegalArgumentException("최대 충전 금액을 초과했습니다.");
        }

        if (chargePoint < MIN_CHARGE_POINT) {
            throw new IllegalArgumentException("최소 충전 금액을 충족하지 못했습니다.");
        }

        Point point = pointRepository.findByUserId(userId);

        point.chargeUserPoint(chargePoint);

        return point;
    }
}