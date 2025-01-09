package com.ecommerce.interfaces.api.v1.point.res;

public record ChargeUserAmountRes(
        Long userId,
        Long chargePoint,
        Long amount
) {
}