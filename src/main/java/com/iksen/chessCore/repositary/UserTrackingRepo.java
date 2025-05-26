package com.iksen.chessCore.repositary;

import org.springframework.data.jpa.repository.JpaRepository;


import com.iksen.chessCore.model.UserHistory;

public interface UserTrackingRepo extends JpaRepository<UserHistory, Long>{

}
