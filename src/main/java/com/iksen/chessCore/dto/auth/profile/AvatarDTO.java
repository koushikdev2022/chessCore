package com.iksen.chessCore.dto.auth.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvatarDTO {
        @JsonProperty("id")
        private Long id;


        @JsonProperty("avatar")
        private String avatar;
}
