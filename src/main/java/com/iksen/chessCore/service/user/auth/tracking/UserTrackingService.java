package com.iksen.chessCore.service.user.auth.tracking;

import com.iksen.chessCore.dto.auth.logTracking.UserHistoryDTO;
import com.iksen.chessCore.model.UserHistory;

public interface UserTrackingService {
    UserHistoryDTO saveHistory(UserHistoryDTO userHistoryDTO);
}
