package com.iksen.chessCore.mapper.country;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.iksen.chessCore.dto.auth.registration.UserDTO;
import com.iksen.chessCore.dto.country.CountryDTO;
import com.iksen.chessCore.mapper.UserMapper;
import com.iksen.chessCore.model.Country;
import com.iksen.chessCore.model.User;

public class CountryMapper {
        public static CountryDTO toDTO(Country country) {
        CountryDTO dto = new CountryDTO();
        dto.setId(country.getId());
        dto.setCountryName(country.getCountryName());
        dto.setCountryShortName(country.getCountryShortName());
        dto.setStatus(country.getStatus());
        return dto;
    }
    public static List<CountryDTO> toDTOList(List<Country> country) {
        if (country == null) return Collections.emptyList();

        return country.stream()
                .map(CountryMapper::toDTO)
                .collect(Collectors.toList());
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
