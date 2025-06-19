package com.inna.book_management_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Year;

public class YearNotInFutureValidator implements ConstraintValidator<YearNotInFuture, Integer> {

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        if (year == null) {
            return true; // null values should be handled by @NotNull
        }
        return year <= Year.now().getValue();
    }
}
