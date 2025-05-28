package com.iksen.chessCore.serviceImpl.user.auth.profile;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.auth.profile.AvatarDTO;
import com.iksen.chessCore.model.User;
import com.iksen.chessCore.repositary.UserRepository;
import com.iksen.chessCore.service.user.auth.profile.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService{
        @Autowired
        private UserRepository userRepository;
        @Override
        public boolean avataUpload(AvatarDTO avatarDTO){
             Optional<User> optionalUser = userRepository.findById(avatarDTO.getId());
            if (optionalUser.isEmpty()) {
                return false;
            }
            User user = optionalUser.get();
            user.setAvatar(avatarDTO.getAvatar()); 
            userRepository.save(user);

            return true;
        }
}
