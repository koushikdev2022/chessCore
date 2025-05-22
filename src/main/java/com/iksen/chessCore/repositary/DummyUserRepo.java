package com.iksen.chessCore.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iksen.chessCore.model.DummyUser;


public interface DummyUserRepo extends JpaRepository<DummyUser, Long> {

}
