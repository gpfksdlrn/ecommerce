package com.ecommerce.application.point;

import com.ecommerce.domain.point.Point;
import com.ecommerce.domain.point.PointService;
import com.ecommerce.domain.user.UserService;
import com.ecommerce.domain.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PointFacade {
    private final PointService pointService;
    private final UserService userService;

    public GetUserAmountResult getUserPoint(Long userId) {
        Users user = userService.getUser(userId);
        Point point = pointService.getUserPoint(user.getId());

        return new GetUserAmountResult(user.getId(), user.getUserMail(), point.getUserAmount());
    }

    public ChargeUserAmountResult chargeUserPoint(Long userId, Long chargePoint) {
        Users user = userService.getUser(userId);
        Point point = pointService.chargeUserPoint(user.getId(), chargePoint);

        return new ChargeUserAmountResult(user.getId(), chargePoint, point.getUserAmount());

    }
}