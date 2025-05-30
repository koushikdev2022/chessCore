package com.iksen.chessCore.mapper.state;

import com.iksen.chessCore.dto.State.StateDTO;
import com.iksen.chessCore.model.State;

public class StateMapper {

    public static StateDTO toDTO(State state) {
        
        if (state == null) return null;

        StateDTO dto = new StateDTO();
        dto.setId(state.getId());
        dto.setStateName(state.getStateName());
        dto.setStateShortName(state.getStateShortName());
        dto.setCountryId(state.getCountryId());
        dto.setStatus(state.getStatus());
        return dto;
    }

    public static State toEntity(StateDTO dto) {
        if (dto == null) return null;

        return State.builder()
                .id(dto.getId())
                .stateName(dto.getStateName())
                .stateShortName(dto.getStateShortName())
                .countryId(dto.getCountryId())
                .status(dto.getStatus())
                .build();
    }
}