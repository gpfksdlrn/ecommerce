package com.ecommerce.application.point;

public record ChargeUserAmountResult(
        Long userId,
        Long chargePoint,
        Long amount
) {
}