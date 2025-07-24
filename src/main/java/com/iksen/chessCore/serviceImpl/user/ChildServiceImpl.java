package com.iksen.chessCore.serviceImpl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.auth.registration.UserDTO;
import com.iksen.chessCore.mapper.UserMapper;
import com.iksen.chessCore.model.User;
import com.iksen.chessCore.repositary.UserRepository;
import com.iksen.chessCore.service.user.child.ChildService;

@Service
public class ChildServiceImpl implements ChildService{
          @Autowired
          private UserRepository userRepository;
          public List<UserDTO> childDetails(Long id){
               List<User> data = userRepository.findByParentId(id);
               List<UserDTO> dtoData = UserMapper.toDTOList(data);
               return dtoData;
          }
}
