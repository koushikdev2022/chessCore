package com.iksen.chessCore.service.user.child;

import java.util.List;

import com.iksen.chessCore.dto.auth.registration.UserDTO;

public interface ChildService {
            List<UserDTO> childDetails(Long id);
}
