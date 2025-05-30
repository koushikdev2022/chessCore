package com.iksen.chessCore.mapper.country;

import com.iksen.chessCore.dto.country.CountryDTO;
import com.iksen.chessCore.model.Country;

public class CountryMapper {
        public static CountryDTO toDTO(Country country) {
        CountryDTO dto = new CountryDTO();
        dto.setId(country.getId());
        dto.setCountryName(country.getCountryName());
        dto.setCountryShortName(country.getCountryShortName());
        dto.setStatus(country.getStatus());
        return dto;
    }

    public static Country toEntity(CountryDTO dto) {
        return Country.builder()
                .id(dto.getId())
                .countryName(dto.getCountryName())
                .countryShortName(dto.getCountryShortName())
                .status(dto.getStatus())
                .build();
    }
}
