// LoginDTO.java
package com.iksen.chessCore.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {
    
    @JsonProperty(value = "userNameOrEmail", access = JsonProperty.Access.WRITE_ONLY)
    private String userNameOrEmail;
    
    @JsonProperty(value = "username", access = JsonProperty.Access.WRITE_ONLY)
    private String userName;
    
    @JsonProperty("password")
    private String password;
    
    // Custom getter that handles both field names
    public String getUserNameOrEmail() {
        // Return whichever field is not null/empty
        if (userNameOrEmail != null && !userNameOrEmail.trim().isEmpty()) {
            return userNameOrEmail;
        }
        if (userName != null && !userName.trim().isEmpty()) {
            return userName;
        }
        return userNameOrEmail; // fallback
    }
    
    // Setter methods
    public void setUserNameOrEmail(String userNameOrEmail) {
        this.userNameOrEmail = userNameOrEmail;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
}