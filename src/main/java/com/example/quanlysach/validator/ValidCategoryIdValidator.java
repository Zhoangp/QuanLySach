package com.example.quanlysach.validator;

import com.example.quanlysach.Model.Category;
import com.example.quanlysach.validator.annotation.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category category, ConstraintValidatorContext context) {
        return category != null;
    }
}
