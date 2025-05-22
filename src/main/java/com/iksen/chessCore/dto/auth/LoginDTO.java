package com.iksen.chessCore.dto.auth;



import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @JsonProperty("user_name")
    private String userNameOrEmail;

    @NotBlank(message = "Password is required")
    private String password;
}
