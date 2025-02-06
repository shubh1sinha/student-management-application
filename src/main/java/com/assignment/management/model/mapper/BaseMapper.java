package com.assignment.management.model.mapper;

import java.util.Optional;

public interface BaseMapper<L, R> {
    R mapToRight(L input);

    L mapToLeft(R input);

    default R mapToRight(L input, R defaultValue) {
        return Optional.ofNullable(input).map(this::mapToRight).orElse(defaultValue);
    }

    default L mapToLeft(R input, L defaultValue) {
        return Optional.ofNullable(input).map(this::mapToLeft).orElse(defaultValue);
    }
}
