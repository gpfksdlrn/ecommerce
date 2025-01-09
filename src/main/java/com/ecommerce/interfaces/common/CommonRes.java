package com.ecommerce.interfaces.common;

import com.ecommerce.interfaces.exception.ExceptionMessage;

public record CommonRes<T>(
        ResultType resultType,
        T data,
        ExceptionMessage message
) {
    public static <T> CommonRes<T> success(T data) {
        return new CommonRes<>(ResultType.SUCCESS, data, new ExceptionMessage());
    }
}
