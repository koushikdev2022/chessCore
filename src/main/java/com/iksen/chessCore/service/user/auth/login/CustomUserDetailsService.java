package com.iksen.chessCore.service.user.auth.login;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.model.User;
import com.iksen.chessCore.serviceImpl.user.auth.login.LoginServiceImpl;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = loginServiceImpl.findByUsernameOrEmail(username);
        
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        
        User user = userOptional.get();
        
        // Check if user is active and not deleted
        if (user.getStatus() != 1 || user.getIsDeleted() != 0) {
            throw new UsernameNotFoundException("User account is inactive or deleted: " + username);
        }
        
        // Determine roles based on parentId
        String[] roles;
        if (user.getParentId() == 0L) {
            roles = new String[]{"PARENT", "USER"};
        } else {
            roles = new String[]{"CHILD", "USER"};
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(roles)
                .accountExpired(false)
                .accountLocked(user.getStatus() != 1)
                .credentialsExpired(false)
                .disabled(user.getIsDeleted() != 0)
                .build();
    }
}