package com.iksen.chessCore.service.user.auth.registration;

import java.util.Optional;


import com.iksen.chessCore.dto.auth.registration.FirstStepUserDTO;
import com.iksen.chessCore.dto.auth.registration.SecondStepDTO;
import com.iksen.chessCore.model.DummyUser;

public interface RegistrationService {
           Optional<DummyUser> firstStep(FirstStepUserDTO firstStepUserDTO);
           Optional<SecondStepDTO> secondStep(SecondStepDTO secondStepDTO);
}
