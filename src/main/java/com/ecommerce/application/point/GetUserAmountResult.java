package com.ecommerce.application.point;

public record GetUserAmountResult(
        Long userId,
        String userMail,
        Long userAmount
) {
}