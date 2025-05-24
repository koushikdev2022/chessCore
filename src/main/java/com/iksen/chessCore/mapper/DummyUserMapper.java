package com.iksen.chessCore.mapper;

import com.iksen.chessCore.model.DummyUser;
import com.iksen.chessCore.dto.auth.registration.DummyUserDTO;
import com.iksen.chessCore.dto.auth.registration.UserDTO;
import com.iksen.chessCore.model.User;

public class DummyUserMapper {

    public static DummyUserDTO toDTO(DummyUser user) {
        if (user == null) return null;

        return DummyUserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .mobile(user.getMobile())
                .avatar(user.getAvatar())
                .otp(user.getOtp())
                .otpExpire(user.getOtpExpire())
                .oAuth(user.getOAuth())
                .oauthProvider(user.getOauthProvider())
                .parentId(user.getParentId())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
    public static UserDTO toUserDTO(DummyUserDTO dto){
        if (dto == null) return null;
        UserDTO user = new UserDTO();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUserName(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setMobile(dto.getMobile());
        user.setAvatar(dto.getAvatar());
        user.setOtp(dto.getOtp());
        user.setOtpExpire(dto.getOtpExpire());
        user.setOAuth(dto.getOAuth());
        user.setOauthProvider(dto.getOauthProvider());
        user.setParentId(dto.getParentId());
        user.setIsDeleted(0);
        user.setStatus(1);
       

        return user;

    }
    
    public static DummyUser toEntity(DummyUserDTO dto) {
        if (dto == null) return null;

        DummyUser user = new DummyUser();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setMobile(dto.getMobile());
        user.setAvatar(dto.getAvatar());
        user.setOtp(dto.getOtp());
        user.setOtpExpire(dto.getOtpExpire());
        user.setOAuth(dto.getOAuth());
        user.setOauthProvider(dto.getOauthProvider());
        user.setParentId(dto.getParentId());
        user.setCreatedAt(dto.getCreatedAt());
        user.setUpdatedAt(dto.getUpdatedAt());

        return user;
    }
}