package com.iksen.chessCore.serviceImpl.user.state;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.State.StateDTO;
import com.iksen.chessCore.mapper.state.StateMapper;
import com.iksen.chessCore.model.State;
import com.iksen.chessCore.repositary.StateRepo;
import com.iksen.chessCore.service.user.state.StateService;

@Service
public class StateServiceImpl implements StateService{
        @Autowired
        private StateRepo stateRepo;
        @Override
        public List<StateDTO> state(Long countryId){
            List<State> states = stateRepo.findByCountryIdAndStatusOrderByStateNameAsc(countryId, 1);
            List<StateDTO> stateData =  StateMapper.toDTOList(states);
            return stateData;
        }
}
