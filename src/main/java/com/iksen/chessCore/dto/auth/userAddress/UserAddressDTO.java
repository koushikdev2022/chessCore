package com.iksen.chessCore.dto.auth.userAddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddressDTO {

    private Long id;
    private Long userId;

    @NotNull(message="country_id canot be blank")
    @JsonProperty("country_id")
    private Long countryId;

    @NotNull(message="state_id canot be blank")
    @JsonProperty("state_id")
    private Long stateId;

    @NotBlank(message="first_line canot be blank")
    @JsonProperty("first_line")
    private String firstLine;

    @JsonProperty("second_line")
    private String secondLine;

    @JsonProperty("zip")
    private String zip;

    @JsonProperty("area_no")
    private String areaNo;

    @JsonProperty("flat_no")
    private String flatNo;


    @JsonProperty("post")
    private String post;

    @JsonProperty("ps")
    private String ps;
    

    @NotBlank(message="lat canot be blank")
    @JsonProperty("lat")
    private String lat;

    @NotBlank(message="longitude canot be blank")
    @JsonProperty("longitude")
    private String longitude;

    @Builder.Default
    @JsonProperty("status")
    private Integer status = 1;

    @Builder.Default
    @JsonProperty("is_primary")
    private Integer isPrimary = 1;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}