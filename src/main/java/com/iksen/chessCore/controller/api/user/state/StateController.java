package com.iksen.chessCore.controller.api.user.state;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.dto.State.StateDTO;
import com.iksen.chessCore.serviceImpl.user.state.StateServiceImpl;

@RestController
@RequestMapping("/api/state")
public class StateController {
    @Autowired
    private StateServiceImpl stateServiceImpl;

    @GetMapping("/list/{id}")
    public ResponseEntity<?> state(@PathVariable("id") Long id){
                try{
                     if (id == null || id <= 0) {
                        return ResponseEntity.status(422).body(Map.of(
                            "status", false,
                            "message", "country id needed",
                            "status_code", 422
                        ));
                     }
                     List<StateDTO> state = stateServiceImpl.state(id);
                     if(!state.isEmpty()){
                         return ResponseEntity.status(200).body(Map.of(
                                    "status", true,
                                    "message", "state found",
                                    "status_code", 200,
                                    "state",state
                          ));
                     }else{
                             return ResponseEntity.status(200).body(Map.of(
                                    "status", true,
                                    "message", "no state found",
                                    "status_code", 200,
                                    "state",state
                             ));
                     }
                    
                 } catch (Exception e) {
                            return ResponseEntity.status(400).body(Map.of(
                                    "status", false,
                                    "message", "something went wrong",
                                    "error",e,
                                    "status_code", 400
                            ));
                }
    }

    
                
    
}
