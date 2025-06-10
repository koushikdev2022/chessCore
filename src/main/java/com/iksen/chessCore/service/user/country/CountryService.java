package com.iksen.chessCore.service.user.country;

import java.util.List;

import com.iksen.chessCore.dto.country.CountryDTO;

public interface CountryService {
        List<CountryDTO> list();
        List<CountryDTO>  findCountryName(String countryName);
}
