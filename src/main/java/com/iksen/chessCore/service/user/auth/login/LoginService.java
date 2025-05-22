package com.iksen.chessCore.service.user.auth.login;

import java.util.Optional;

import com.iksen.chessCore.dto.auth.LoginDTO;
import com.iksen.chessCore.model.User;

public interface LoginService {
        Optional<?> login(LoginDTO loginDTO);
        Optional<User> findByUsernameOrEmail(String usernameOrEmail) ;
}
