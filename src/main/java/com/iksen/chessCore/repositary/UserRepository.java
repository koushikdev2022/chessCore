package com.iksen.chessCore.repositary;


import org.springframework.data.jpa.repository.JpaRepository;

import com.iksen.chessCore.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByUserNameOrEmail(String userName, String email);
     Optional<User> findByUserNameOrEmailAndStatusAndIsDeleted(String userName, String email,Integer status,Integer delete);
     Optional<User> findByUserName(String userName);
     Optional<User> findByEmail(String email);
     boolean existsByEmail(String email);
     boolean existsByUserName(String userName);
     List<User> findByParentId(Long id);
}