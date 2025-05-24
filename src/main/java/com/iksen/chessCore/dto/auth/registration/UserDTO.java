package com.iksen.chessCore.dto.auth.registration;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("f_name")
    private String firstName;

    @JsonProperty("l_name")
    private String lastName;

    @JsonProperty("username")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("o_auth")
    private String oAuth;

    @JsonProperty("oauth_provider")
    private String oauthProvider;

    @JsonProperty("otp")
    private Integer otp;

    @JsonProperty("otp_expire")
    private LocalDateTime otpExpire;
    

    @JsonProperty("parent_id")
    private Long parentId = 0L;

    @JsonProperty("status")
    private Integer status = 1;

    @JsonProperty("is_deleted")
    private Integer isDeleted = 0;

    private LocalDateTime createdAt;

    
    private LocalDateTime updatedAt;
}
