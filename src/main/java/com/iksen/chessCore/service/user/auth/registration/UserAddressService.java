package com.iksen.chessCore.service.user.auth.registration;

import java.util.Optional;

import com.iksen.chessCore.dto.auth.registration.SecondStepDTO;

import com.iksen.chessCore.dto.auth.userAddress.UserAddressDTO;

public interface UserAddressService {
    UserAddressDTO saveAddress(SecondStepDTO secondStepDTO);
    UserAddressDTO saveAddressToData(UserAddressDTO userAddressDTO);
    boolean primaryUpdate(Long id);
}
