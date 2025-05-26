package com.iksen.chessCore.service.user.auth.login;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.config.CustomUserDetails;
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

        if (user.getStatus() != 1 || user.getIsDeleted() != 0) {
            throw new UsernameNotFoundException("User account is inactive or deleted: " + username);
        }

        String[] roles = user.getParentId() == 0L
            ? new String[]{"PARENT", "USER"}
            : new String[]{"CHILD", "USER"};

        List<SimpleGrantedAuthority> authorities = Arrays.stream(roles)
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new CustomUserDetails(user, authorities); // ✅ Pass authorities here
    }
}