package com.iksen.chessCore.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iksen.chessCore.model.UserAddress;

public interface UserAddressRepo extends JpaRepository<UserAddress, Long> {
    List<UserAddress> findByUserId(Long id);
    List<UserAddress> findByUserIdAndIdNot(Long userId,Long id);
    
}
