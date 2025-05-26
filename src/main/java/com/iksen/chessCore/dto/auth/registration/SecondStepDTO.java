package com.iksen.chessCore.dto.auth.registration;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecondStepDTO {


    @NotNull(message = "id cannot be blank")
    @JsonProperty("user_id")
    private Long user_id;

    @NotBlank(message = "mobile cannot be blank")
    @JsonProperty("mobile")
    private String mobile;


    private Long id;

    @NotNull(message = "country_id cannot be blank")
    @JsonProperty("country_id")
    private Long countryId;


    @NotNull(message = "state_id cannot be blank")
    @JsonProperty("state_id")
    private Long stateId;

    @NotBlank(message = "first_Line cannot be blank")
    @JsonProperty("first_line")
    private String firstLine;

    @JsonProperty("second_line")
    private String secondLine;
    
    @NotBlank(message = "zip cannot be blank")
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

    @JsonProperty("lat")
    private String lat;

    @JsonProperty("long")
    private String longitude;

    @Builder.Default
    @JsonProperty("status")
    private Integer status = 1;

}
