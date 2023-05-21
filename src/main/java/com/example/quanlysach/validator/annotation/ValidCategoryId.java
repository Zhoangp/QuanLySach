package com.example.quanlysach.validator.annotation;

import jakarta.validation.Payload;

public @interface ValidCategoryId {
    String message() default "Invalid Category Id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
