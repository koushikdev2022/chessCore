package com.iksen.chessCore.serviceImpl.batch;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.batch.UserBatchMapDTO;
import com.iksen.chessCore.mapper.batch.UserBatchMapMapper;
import com.iksen.chessCore.model.UserBatchMap;
import com.iksen.chessCore.repositary.UserBatchMapRepository;
import com.iksen.chessCore.service.batch.UserBatchMapService;

@Service
public class UserBatchMapServiceImpl implements UserBatchMapService {
    @Autowired
    private UserBatchMapRepository userBatchMapRepository;
    public List<UserBatchMapDTO> totalBatch(Long id){
        List<UserBatchMap> userDataList = userBatchMapRepository.findAllByUserId(id);
        System.err.println(userDataList+"userDataList");
        List<UserBatchMapDTO> userDataListDTO = UserBatchMapMapper.toDTOList(userDataList);
        return userDataListDTO;
    }
}
