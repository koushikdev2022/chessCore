package com.iksen.chessCore.controller.api.user.profile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iksen.chessCore.dto.auth.profile.AvatarDTO;
import com.iksen.chessCore.serviceImpl.user.auth.profile.ProfileServiceImpl;
import com.iksen.chessCore.utill.JwtUtill;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profile")
public class ProfileController{
            @Autowired
            private JwtUtill jwtUtill;
            @Autowired
            private ProfileServiceImpl profileServiceImpl;
            @PostMapping("/avatar")
            public ResponseEntity<?> avatar(@Valid 
                    @RequestParam("avatar") MultipartFile avatarFile) {
                try {
                    
                    Long userId = jwtUtill.getCurrentUserId();
                    if (avatarFile.isEmpty()) {
                         return ResponseEntity.ok(Map.of(
                            "status", false,
                            "message", "no image file",
                            "status_code", 422
                         ));
                    }
                    try {
                        String projectRoot = new File("").getAbsolutePath(); 
                        String userUploadDirPath = projectRoot + "/public/uploads/avatar/" + userId + "/";
                        File userUploadDir = new File(userUploadDirPath);
                        if (!userUploadDir.exists()) {
                            userUploadDir.mkdirs();
                        }
                        String originalFilename = avatarFile.getOriginalFilename();
                        String fileExtension = "";
                        if (originalFilename != null && originalFilename.contains(".")) {
                            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                            originalFilename = originalFilename.substring(0, originalFilename.lastIndexOf(".")); 
                        }
                        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                        String newFileName = originalFilename + "_" + timestamp + fileExtension;
                        AvatarDTO avatarDTO= new AvatarDTO();
                        String filePath = userUploadDirPath + newFileName;
                        avatarFile.transferTo(new File(filePath));
                        avatarDTO.setAvatar("/public/uploads/avatar/" + userId + "/" + newFileName); 
                        avatarDTO.setId(userId);
                        boolean saveData = profileServiceImpl.avataUpload(avatarDTO); 
                        if(saveData){
                                return ResponseEntity.status(200).body(Map.of(
                                    "status", true,
                                    "message", "avatar upload successfully",
                                    "status_code", 200
                                    
                                ));
                        }else{
                              return ResponseEntity.status(201).body(Map.of(
                                    "status", true,
                                    "message", "character insert successfully",
                                    "status_code", 200
                                    
                                ));
                        }
                  
                    } catch (IOException e) {
                           return ResponseEntity.ok(Map.of(
                                "status", false,
                                "message", "failed to upload",
                                "status_code", 400
                           ));
                    }
                
                } catch (Exception e) {
                 
                    return ResponseEntity.status(400).body(Map.of(
                            "status", false,
                            "message", "An error occurred: " + e.getMessage(),
                            "status_code", 400
                    ));
                }
            }
}
