package com.iksen.chessCore.mapper;

import com.iksen.chessCore.dto.auth.registration.SecondStepDTO;
import com.iksen.chessCore.dto.auth.userAddress.UserAddressDTO;
import com.iksen.chessCore.model.UserAddress;

public class UserAddressMapper {
          public static UserAddressDTO toSecondToAddressDTO(SecondStepDTO user) {
              if (user == null) return null;

              return UserAddressDTO.builder()
                      .countryId(user.getCountryId())
                      .stateId(user.getStateId())
                      .firstLine(user.getFirstLine())
                      .secondLine(user.getSecondLine())
                      .zip(user.getZip())
                      .areaNo(user.getAreaNo())
                      .userId(user.getUser_id())
                      .flatNo(user.getFlatNo())
                      .post(user.getPost())
                      .ps(user.getPs())
                      .lat(user.getLat())
                      .longitude(user.getLongitude())
                      .status(user.getStatus())
                      .isPrimary(user.getIsPrimary())
                      .build();
          }
            public static UserAddress toUserAddress(UserAddressDTO user) {
              if (user == null) return null;

              return UserAddress.builder()
                      .countryId(user.getCountryId())
                      .stateId(user.getStateId())
                      .firstLine(user.getFirstLine())
                      .secondLine(user.getSecondLine())
                      .zip(user.getZip())
                      .areaNo(user.getAreaNo())
                      .userId(user.getUserId())
                      .flatNo(user.getFlatNo())
                      .post(user.getPost())
                      .ps(user.getPs())
                      .lat(user.getLat())
                      .longitude(user.getLongitude())
                      .status(user.getStatus())
                      .isPrimary(user.getIsPrimary())
                      .build();
          }
          public static UserAddressDTO toDTO(UserAddress address) {
              if (address == null) return null;

              return UserAddressDTO.builder()
                      .id(address.getId())
                      .userId(address.getUserId())
                      .countryId(address.getCountryId())
                      .stateId(address.getStateId())
                      .firstLine(address.getFirstLine())
                      .secondLine(address.getSecondLine())
                      .zip(address.getZip())
                      .areaNo(address.getAreaNo())
                      .flatNo(address.getFlatNo())
                      .post(address.getPost())
                      .ps(address.getPs())
                      .lat(address.getLat())
                      .longitude(address.getLongitude())
                      .status(address.getStatus())
                      .isPrimary(address.getIsPrimary())
                      .createdAt(address.getCreatedAt())
                      .updatedAt(address.getUpdatedAt())
                      .build();
          }
}
