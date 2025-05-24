package com.iksen.chessCore.serviceImpl.user.auth.registration;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.auth.registration.DummyUserDTO;
import com.iksen.chessCore.dto.auth.registration.FirstStepUserDTO;
import com.iksen.chessCore.dto.auth.registration.SecondStepDTO;
import com.iksen.chessCore.dto.auth.registration.UserDTO;
import com.iksen.chessCore.mapper.DummyUserMapper;
import com.iksen.chessCore.mapper.UserMapper;
import com.iksen.chessCore.model.DummyUser;
import com.iksen.chessCore.model.User;
import com.iksen.chessCore.repositary.DummyUserRepo;
import com.iksen.chessCore.repositary.UserRepository;
import com.iksen.chessCore.service.user.auth.registration.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
                @Autowired
                private  PasswordEncoder passwordEncoder;
                @Autowired
                private DummyUserRepo dummyUserRepository;
                @Autowired
                private UserRepository userRepo;

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
                @Override
                public Optional<DummyUserDTO> findDummyUser(Long id){
                    return dummyUserRepository.findById(id)
                    .map(DummyUserMapper::toDTO);
                }
                @Override
                public Optional<UserDTO> saveUser(UserDTO userDTO){
                    if (userDTO.getId() != null && !userRepo.existsById(userDTO.getId())) {
                        throw new RuntimeException("Trying to update a non-existent user");
                    }
                
                    User user = UserMapper.toUser(userDTO);
                    User createUser = userRepo.saveAndFlush(user);  // Flush to detect DB constraint issues early
                    UserDTO savedUserDTO = UserMapper.toDTO(createUser);
                    return Optional.ofNullable(savedUserDTO); 
                }
                
}
