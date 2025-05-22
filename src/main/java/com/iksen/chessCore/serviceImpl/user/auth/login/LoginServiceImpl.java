package com.iksen.chessCore.serviceImpl.user.auth.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.auth.LoginDTO;
import com.iksen.chessCore.model.User;
import com.iksen.chessCore.repositary.UserRepository;
import com.iksen.chessCore.service.user.auth.login.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
        @Autowired
        private UserRepository userRepository;
        @Override
        public Optional<User> login(LoginDTO loginDTO) { // Fixed return type from Optional<?> to Optional<User>
            // Find active user by username or email
            Optional<User> userOptional = userRepository.findByUserNameOrEmailAndStatusAndIsDeleted(
                loginDTO.getUserNameOrEmail(), 
                loginDTO.getUserNameOrEmail(),
                1,
                0  
            );
            
            return userOptional;
        }
        @Override
        public Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
            return userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail);
        }
}
