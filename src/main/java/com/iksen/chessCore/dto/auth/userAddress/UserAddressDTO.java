package com.iksen.chessCore.dto.auth.userAddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddressDTO {

    private Long id;
    private Long userId;

    private Long countryId;
    private Long stateId;

    private String firstLine;
    private String secondLine;

    private String zip;
    private String areaNo;
    private String flatNo;

    private String post;
    private String ps;

    private String lat;
    private String longitude;

    @Builder.Default
    @JsonProperty("status")
    private Integer status = 1;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}