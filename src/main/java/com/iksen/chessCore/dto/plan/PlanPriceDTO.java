package com.iksen.chessCore.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanPriceDTO {

    private Long id;
    private String currency;
    private String price;
    private Long countryId;
    private Integer status;
}
