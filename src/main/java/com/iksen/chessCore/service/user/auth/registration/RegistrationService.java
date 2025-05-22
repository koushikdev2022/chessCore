package com.iksen.chessCore.service.user.auth.registration;

import java.util.Optional;


import com.iksen.chessCore.dto.auth.registration.FirstStepUserDTO;

public interface RegistrationService {
            Optional<?> firstStep(FirstStepUserDTO firstStepUserDTO);
}
