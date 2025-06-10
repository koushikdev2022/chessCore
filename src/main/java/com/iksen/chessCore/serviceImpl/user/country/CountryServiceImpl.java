package com.iksen.chessCore.serviceImpl.user.country;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.country.CountryDTO;
import com.iksen.chessCore.mapper.country.CountryMapper;
import com.iksen.chessCore.model.Country;
import com.iksen.chessCore.repositary.CountryRepo;
import com.iksen.chessCore.service.user.country.CountryService;

@Service
public class CountryServiceImpl implements CountryService{
                @Autowired
                private CountryRepo countryRepo;
                @Override
                public List<CountryDTO> list(){
                    List<Country> country = countryRepo.findByStatusOrderByCountryNameAsc(1);
                    List<CountryDTO> coun = CountryMapper.toDTOList(country);
                    return coun;
                }
                @Override
                public List<CountryDTO> findCountryName(String countryName){
                    List<Country> country =  countryRepo.findFirstByStatusAndCountryNameStartingWith(1, countryName);
                    List<CountryDTO> coun = CountryMapper.toDTOList(country);
                    return coun;
                }
}
