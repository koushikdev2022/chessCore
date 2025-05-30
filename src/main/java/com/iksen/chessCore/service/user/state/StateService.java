package com.iksen.chessCore.service.user.state;

import java.util.List;

import com.iksen.chessCore.dto.State.StateDTO;

public interface StateService {
        List<StateDTO> state(Long countryId);
}
