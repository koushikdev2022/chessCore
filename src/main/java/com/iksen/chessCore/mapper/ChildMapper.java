package com.iksen.chessCore.mapper;

import java.time.LocalDateTime;

import com.iksen.chessCore.dto.auth.registration.ChildDTO;
import com.iksen.chessCore.model.User;

public class ChildMapper {
            public static User fromChildDTOToUser(ChildDTO dto) {
                if (dto == null) return null;
                return User.builder()
                        .firstName(dto.getFirstName())
                        .lastName(dto.getLastName())
                        .userName(dto.getUserName())
                        .email(dto.getEmail())
                        .password(dto.getPassword())
                        .mobile(dto.getMobile())
                        .avatar(dto.getAvatar())
                        .oAuth(dto.getOAuth())
                        .oauthProvider(dto.getOauthProvider())
                        .otp(dto.getOtp())
                        .otpExpire(dto.getOtpExpire())
                        .parentId(dto.getParentId())
                        .status(dto.getStatus())
                        .isDeleted(dto.getIsDeleted())
                        .createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now())
                        .updatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt() : LocalDateTime.now())
                        .build();
            }

            public static ChildDTO fromUserToChildDTO(User user) {
                if (user == null) return null;
                return ChildDTO.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .userName(user.getUserName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .mobile(user.getMobile())
                        .avatar(user.getAvatar())
                        .oAuth(user.getOAuth())
                        .oauthProvider(user.getOauthProvider())
                        .otp(user.getOtp())
                        .otpExpire(user.getOtpExpire())
                        .parentId(user.getParentId())
                        .status(user.getStatus())
                        .isDeleted(user.getIsDeleted())
                        .createdAt(user.getCreatedAt())
                        .updatedAt(user.getUpdatedAt())
                        .build();
            }
}
