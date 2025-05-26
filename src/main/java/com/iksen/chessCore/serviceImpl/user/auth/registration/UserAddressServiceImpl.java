package com.iksen.chessCore.serviceImpl.user.auth.registration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.iksen.chessCore.dto.auth.registration.SecondStepDTO;
import com.iksen.chessCore.dto.auth.registration.UserDTO;
import com.iksen.chessCore.dto.auth.userAddress.UserAddressDTO;
import com.iksen.chessCore.mapper.UserAddressMapper;
import com.iksen.chessCore.mapper.UserMapper;
import com.iksen.chessCore.model.UserAddress;
import com.iksen.chessCore.repositary.UserAddressRepo;
import com.iksen.chessCore.repositary.UserRepository;
import com.iksen.chessCore.service.user.auth.registration.UserAddressService;

public class UserAddressServiceImpl implements UserAddressService{
            @Autowired
            private  UserAddressRepo userAddressRepo;
            @Override
            public Optional<UserAddressDTO> saveAddress(SecondStepDTO secondStepDTO){
                UserAddressDTO userAddressDTO = new UserAddressDTO();
                UserAddressDTO convertUserAddressDTO = UserAddressMapper.toSecondToAddressDTO(secondStepDTO);
                UserAddress userAddress = UserAddressMapper.toUserAddress(convertUserAddressDTO);
                UserAddress savedUserAddressDTO = userAddressRepo.save(userAddress);
                UserAddressDTO convertUserAddressDTO2 = UserAddressMapper.toDTO(savedUserAddressDTO);
                return Optional.of(convertUserAddressDTO2);
            }
}
