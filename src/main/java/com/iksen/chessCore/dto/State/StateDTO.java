package com.iksen.chessCore.dto.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateDTO {
    private Long id;
    private String stateName;
    private String stateShortName;
    private Long countryId;
    private Integer status;
}
