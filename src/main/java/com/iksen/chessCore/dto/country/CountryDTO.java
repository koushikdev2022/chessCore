package com.iksen.chessCore.dto.country;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
    private Integer id;
    private String countryName;
    private String countryShortName;
    private Integer status;
}
