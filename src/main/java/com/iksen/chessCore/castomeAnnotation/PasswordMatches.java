package com.iksen.chessCore.castomeAnnotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import com.iksen.chessCore.validation.PasswordMatchesValidator;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "Password and confirm password must match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}