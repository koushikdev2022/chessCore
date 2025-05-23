package com.iksen.chessCore.dto.auth.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.iksen.chessCore.castomeAnnotation.PasswordMatches;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PasswordMatches // <- Custom class-level validation annotation
public class FirstStepUserDTO {

    @JsonProperty("username")
    private String userName;

    @NotBlank(message = "First name cannot be blank")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "Confirm password cannot be blank")
    @JsonProperty("confirm_password")
    private String confirmPassword;

    @NotNull(message = "Parent ID cannot be null")
    @JsonProperty("parent_id")
    private Long parentId;
}
