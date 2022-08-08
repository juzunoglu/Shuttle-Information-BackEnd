package com.alihan.uzunoglu.twilio.service;

import com.alihan.uzunoglu.twilio.entity.Role;
import com.alihan.uzunoglu.twilio.entity.User;

import java.util.List;

public interface UserService {

	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	User getUserByUserName(String username);
	List<User> getAllUsers();
}
