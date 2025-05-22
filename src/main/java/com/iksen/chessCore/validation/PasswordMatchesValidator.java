package com.iksen.chessCore.validation;



import com.iksen.chessCore.dto.auth.registration.FirstStepUserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.iksen.chessCore.castomeAnnotation.PasswordMatches;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, FirstStepUserDTO> {

    @Override
    public boolean isValid(FirstStepUserDTO dto, ConstraintValidatorContext context) {
        if (dto.getPassword() == null || dto.getConfirmPassword() == null) {
            return false;
        }
        return dto.getPassword().equals(dto.getConfirmPassword());
    }
}
