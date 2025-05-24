package com.iksen.chessCore.dto.auth.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DummyUserDTO {

    private Long id;

    @JsonProperty("f_name")
    private String firstName;

    @JsonProperty("l_name")
    private String lastName;

    private String username;

    private String email;

    @JsonProperty("password")
    private String password;

    private String mobile;

    private String avatar;

    private Integer otp;

    @JsonProperty("otp_expire")
    private LocalDateTime otpExpire;

    @JsonProperty("o_auth")
    private String oAuth;

    @JsonProperty("oauth_provider")
    private String oauthProvider;

    @JsonProperty("parent_id")
    private Long parentId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
