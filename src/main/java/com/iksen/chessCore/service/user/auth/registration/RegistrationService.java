package com.iksen.chessCore.service.user.auth.registration;

import java.util.Optional;

import com.iksen.chessCore.dto.auth.registration.ChildDTO;
import com.iksen.chessCore.dto.auth.registration.DummyUserDTO;
import com.iksen.chessCore.dto.auth.registration.FirstStepUserDTO;
import com.iksen.chessCore.dto.auth.registration.SecondStepDTO;
import com.iksen.chessCore.dto.auth.registration.UserDTO;
import com.iksen.chessCore.model.DummyUser;

public interface RegistrationService {
           Optional<DummyUser> firstStep(FirstStepUserDTO firstStepUserDTO);
           Optional<SecondStepDTO> secondStep(SecondStepDTO secondStepDTO);
           Optional<DummyUserDTO> findDummyUser(Long id);
           Optional<UserDTO> saveUser(UserDTO userDTO);
           boolean deleteDummy(Long id);
           Optional<UserDTO> findEmail(String email);
           Optional<UserDTO> findUsername(String usernname);
           ChildDTO saveDataChild(ChildDTO childDTO);
}
