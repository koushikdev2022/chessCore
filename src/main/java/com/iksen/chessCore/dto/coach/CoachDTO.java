package com.iksen.chessCore.dto.coach;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import com.iksen.chessCore.dto.batch.BatchDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoachDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String mobile;
    private String avatar;
    private Integer otp;
    private LocalDateTime otpExpire;
    private String oAuth;
    private String oauthProvider;
    private Long rmId;
    private Long levelId;
    private Integer status;
    private Integer isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer fidReating;
    private Long opId;

    // Optionally include BatchDTOs
    private List<BatchDTO> batches;
}
