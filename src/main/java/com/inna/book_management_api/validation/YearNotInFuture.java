package com.inna.book_management_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Author: Inna Eisenstark
 * Created: 2025-06-20
 * Description: YearNotInFuture is a custom validation annotation that checks if a year is not in the future.
 */
@Target({ ElementType.FIELD }) // The annotation can be applied to fields
@Retention(RetentionPolicy.RUNTIME) // The annotation is available at runtime
@Constraint(validatedBy = YearNotInFutureValidator.class) // Specifies the validator class that will handle the validation logic
@Documented
public @interface YearNotInFuture {
    String message() default "Year cannot be in the future";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
