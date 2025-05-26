package com.iksen.chessCore.dto.auth.logTracking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserHistoryDTO {

    private Long id;

    private String browser;

    private String ip;

    private String lat;

    private String longitude;

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("device_name")
    private String deviceName;

    @JsonProperty("user_flag")
    private String userFlag;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}

