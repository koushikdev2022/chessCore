package com.iksen.chessCore.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iksen.chessCore.model.UserAddress;

public interface UserAddressRepo extends JpaRepository<UserAddress, Long> {

}
