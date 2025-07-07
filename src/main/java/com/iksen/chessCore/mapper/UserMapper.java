package com.iksen.chessCore.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.iksen.chessCore.dto.auth.registration.DummyUserDTO;
import com.iksen.chessCore.dto.auth.registration.UserDTO;
import com.iksen.chessCore.model.DummyUser;
import com.iksen.chessCore.model.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        if (user == null) return null;

        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .mobile(user.getMobile())
                .avatar(user.getAvatar())
                .otp(user.getOtp())
                .otpExpire(user.getOtpExpire())
                .oAuth(user.getOAuth())
                .oauthProvider(user.getOauthProvider())
                .parentId(user.getParentId())
                .isDeleted(user.getIsDeleted())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static List<UserDTO> toDTOList(List<User> users) {
        if (users == null) return Collections.emptyList();

        return users.stream()
                    .map(UserMapper::toDTO)  
                    .collect(Collectors.toList());
    }
    public static User toUser(UserDTO user) {
        if (user == null) return null;

        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .mobile(user.getMobile())
                .avatar(user.getAvatar())
                .otp(user.getOtp())
                .otpExpire(user.getOtpExpire())
                .oAuth(user.getOAuth())
                .oauthProvider(user.getOauthProvider())
                .parentId(user.getParentId())
                .isDeleted(user.getIsDeleted())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
