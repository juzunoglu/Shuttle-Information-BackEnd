package com.alihan.uzunoglu.twilio.frontEndService;

import com.alihan.uzunoglu.twilio.model.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Override
	public UserInfo getUserInfo(UserInfo userInfo) {
		return userInfo;
	}
}
