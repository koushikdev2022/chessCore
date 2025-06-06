package com.iksen.chessCore.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iksen.chessCore.model.Country;


public interface CountryRepo extends JpaRepository<Country, Long>{
    List<Country>findByStatusOrderByCountryNameAsc(int id);
    
}
