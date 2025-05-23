package com.iksen.chessCore.dto.auth.registration;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
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
    @JsonProperty("id")
    private Long id;

    @NotBlank(message = "mobile cannot be blank")
    @JsonProperty("mobile")
    private String mobile;
    

}
