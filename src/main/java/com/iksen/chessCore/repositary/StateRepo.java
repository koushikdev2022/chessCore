package com.iksen.chessCore.repositary;

import com.iksen.chessCore.model.State;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepo extends JpaRepository<State, Long>{
    
    List<State> findByCountryIdAndStatus(Long countryId,int id);
}
