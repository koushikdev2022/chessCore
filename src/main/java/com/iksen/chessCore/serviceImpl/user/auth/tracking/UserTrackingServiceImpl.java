package com.iksen.chessCore.serviceImpl.user.auth.tracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.auth.logTracking.UserHistoryDTO;
import com.iksen.chessCore.mapper.TrackHistoryMapper;

import com.iksen.chessCore.model.UserHistory;

import com.iksen.chessCore.repositary.UserTrackingRepo;
import com.iksen.chessCore.service.user.auth.tracking.UserTrackingService;

@Service
public class UserTrackingServiceImpl implements UserTrackingService{
    @Autowired
    private  UserTrackingRepo userTrackingRepo;
    @Override
    public UserHistoryDTO saveHistory(UserHistoryDTO userHistoryDTO){
            UserHistory saveDataEntityConvert =  TrackHistoryMapper.toEntity(userHistoryDTO);
            UserHistory saveDataHistory = userTrackingRepo.save(saveDataEntityConvert);
            UserHistoryDTO userHistory =  TrackHistoryMapper.toDTO(saveDataHistory);
            return userHistory;
    }

}
