package com.iksen.chessCore.service.user.auth.profile;

import com.iksen.chessCore.dto.auth.profile.AvatarDTO;

public interface ProfileService {
       boolean avataUpload(AvatarDTO avatarDTO);
}
