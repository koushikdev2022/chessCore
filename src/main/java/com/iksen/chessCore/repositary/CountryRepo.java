package com.iksen.chessCore.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iksen.chessCore.model.Country;


public interface CountryRepo extends JpaRepository<Country, Long>{
    
    
}
