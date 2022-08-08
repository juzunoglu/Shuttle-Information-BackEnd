package com.alihan.uzunoglu.twilio.service.frontEndService;

import com.alihan.uzunoglu.twilio.model.UserShuttleInfoDTO;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Override
	public UserShuttleInfoDTO getUserInfo(UserShuttleInfoDTO userShuttleInfoDTO) {
		return userShuttleInfoDTO;
	}
}
