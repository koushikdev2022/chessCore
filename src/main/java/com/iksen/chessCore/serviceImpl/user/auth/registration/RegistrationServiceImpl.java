package com.iksen.chessCore.serviceImpl.user.auth.registration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.iksen.chessCore.dto.auth.registration.FirstStepUserDTO;
import com.iksen.chessCore.model.DummyUser;
import com.iksen.chessCore.repositary.DummyUserRepo;
import com.iksen.chessCore.repositary.UserRepository;

public class RegistrationServiceImpl {
              @Autowired
                private DummyUserRepo dummyUserRepository;
                @Override
                public Optional<?> firstStep(FirstStepUserDTO firstStepUserDTO){
                    return "k";
                }
}
