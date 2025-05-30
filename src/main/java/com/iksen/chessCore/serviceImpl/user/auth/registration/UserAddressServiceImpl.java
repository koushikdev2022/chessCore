package com.iksen.chessCore.serviceImpl.user.auth.registration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.auth.registration.SecondStepDTO;
import com.iksen.chessCore.dto.auth.registration.UserDTO;
import com.iksen.chessCore.dto.auth.userAddress.UserAddressDTO;
import com.iksen.chessCore.mapper.UserAddressMapper;
import com.iksen.chessCore.mapper.UserMapper;
import com.iksen.chessCore.model.User;
import com.iksen.chessCore.model.UserAddress;
import com.iksen.chessCore.repositary.UserAddressRepo;
import com.iksen.chessCore.repositary.UserRepository;
import com.iksen.chessCore.service.user.auth.registration.UserAddressService;

@Service
public class UserAddressServiceImpl implements UserAddressService{
            @Autowired
            private  UserAddressRepo userAddressRepo;
            @Override
            public UserAddressDTO saveAddress(SecondStepDTO secondStepDTO){
                UserAddressDTO userAddressDTO = new UserAddressDTO();
                UserAddressDTO convertUserAddressDTO = UserAddressMapper.toSecondToAddressDTO(secondStepDTO);
                UserAddress userAddress = UserAddressMapper.toUserAddress(convertUserAddressDTO);
                UserAddress savedUserAddressDTO = userAddressRepo.save(userAddress);
                UserAddressDTO convertUserAddressDTO2 = UserAddressMapper.toDTO(savedUserAddressDTO);
                return convertUserAddressDTO2;
            }
            @Override
            public UserAddressDTO saveAddressToData(UserAddressDTO userAddressDTO){
                  List<UserAddress> userAddress = userAddressRepo.findByUserId(userAddressDTO.getUserId());
                  if(!userAddress.isEmpty()){
                        for (UserAddress address : userAddress) {
                                address.setIsPrimary(0); 
                        }

                        userAddressRepo.saveAll(userAddress);
                  }
                  UserAddress userAddressEntity = UserAddressMapper.toUserAddress(userAddressDTO);
                  UserAddress saveDataAddress =  userAddressRepo.save(userAddressEntity);
                  UserAddressDTO userAddressDtoSaveData = UserAddressMapper.toDTO(saveDataAddress);
                
                  return userAddressDtoSaveData;
            }
            @Override
            public boolean primaryUpdate(Long id){
                  Optional<UserAddress> userAddress = userAddressRepo.findById(id);
                  // List<UserAddress> list = userAddress.map(Collections::singletonList)
                  //                .orElse(Collections.emptyList());
                  userAddress.ifPresent(address -> {
                        address.setIsPrimary(1);           
                        userAddressRepo.save(address);      
                  });
                  
                  if(userAddress.isPresent()){
                    
                        Long user_id = userAddress.get().getUserId();
                        List<UserAddress> userAddressList = userAddressRepo.findByUserIdAndIdNot(user_id,id);
                        for (UserAddress address : userAddressList) {
                                address.setIsPrimary(0); 
                        }

                        userAddressRepo.saveAll(userAddressList);
                  }
                  
                  return true;
            }
}
