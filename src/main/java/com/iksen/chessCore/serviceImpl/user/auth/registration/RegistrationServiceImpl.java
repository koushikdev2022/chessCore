package com.iksen.chessCore.serviceImpl.user.auth.registration;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.auth.registration.FirstStepUserDTO;
import com.iksen.chessCore.dto.auth.registration.SecondStepDTO;
import com.iksen.chessCore.model.DummyUser;
import com.iksen.chessCore.repositary.DummyUserRepo;
import com.iksen.chessCore.repositary.UserRepository;
import com.iksen.chessCore.service.user.auth.registration.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
                @Autowired
                private  PasswordEncoder passwordEncoder;
                @Autowired
                private DummyUserRepo dummyUserRepository;

                // RegistrationServiceImpl(PasswordEncoder passwordEncoder) {
                //     this.passwordEncoder = passwordEncoder;
                // }
                @Override
                public Optional<DummyUser> firstStep(FirstStepUserDTO dto) {
                    System.out.println(dto+"dtogggggggg");
                    DummyUser user = new DummyUser();
                    user.setFirstName(dto.getFirstName());
                    user.setLastName(dto.getLastName());
                    user.setUsername(dto.getUserName());
                    user.setEmail(dto.getEmail());
                    user.setPassword(passwordEncoder.encode(dto.getPassword())); 
                    user.setParentId(dto.getParentId());
                    user.setCreatedAt(LocalDateTime.now());
                    user.setUpdatedAt(LocalDateTime.now());
                    System.out.println(user+"dtofffff");
                    DummyUser savedUser = dummyUserRepository.save(user);
                    return Optional.of(savedUser);
                }
                @Override
                public Optional<SecondStepDTO> secondStep(SecondStepDTO secondStepDTO){
                    
                    return Optional.ofNullable(secondStepDTO);
                }
}
