package com.iksen.chessCore.dto.auth.registration;



import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iksen.chessCore.validation.UniqueEmail;
import com.iksen.chessCore.validation.UniqueUsername;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChildDTO {

    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("f_name")
    @NotBlank(message = "First name is required")
    private String firstName;

    @JsonProperty("l_name")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @JsonProperty("username")
    @NotBlank(message = "Username is required")
    @UniqueUsername
    private String userName;

    @JsonProperty("email")
    @NotBlank(message = "Email is required")
    @UniqueEmail
    private String email;

    @JsonProperty("password")
    @NotBlank(message = "Password is required")
    private String password;

    @JsonProperty("confirm_password")
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

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

