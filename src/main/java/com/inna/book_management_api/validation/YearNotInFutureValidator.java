package com.inna.book_management_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Year;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: YearNotInFuture is a custom validation annotation that checks if a year is not in the future.
 */
public class YearNotInFutureValidator implements ConstraintValidator<YearNotInFuture, Integer> {
    /** Validates that the year is not in the future.
     *
     * @param year the year to validate
     * @param context context provides contextual data and operation when performing validation
     * @return true if the year is not in the future, false otherwise
     */
    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext context) {
        if (year == null) {
            return true; // null values should be handled by @NotNull
        }
        return year <= Year.now().getValue();
    }
}
