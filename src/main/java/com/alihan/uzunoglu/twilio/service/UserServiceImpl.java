package com.alihan.uzunoglu.twilio.service;

import com.alihan.uzunoglu.twilio.entity.Role;
import com.alihan.uzunoglu.twilio.entity.User;
import com.alihan.uzunoglu.twilio.repository.RoleRepository;
import com.alihan.uzunoglu.twilio.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional
public class UserServiceImpl implements UserService {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User saveUser(User user) {
		LOGGER.info("Saving new user with: {}", user.toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		LOGGER.info("Saving new role...");
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		LOGGER.info("Adding a new role: {} to the user: {} " , roleName, username);
		User user = userRepository.findByUsername(username)
						.orElseThrow(() -> new RuntimeException("No userName is found: " + username));
		Role role = roleRepository.findByName(roleName)
						.orElseThrow(() -> new RuntimeException("That role is not yet defined: " + roleName));

		user.getRoles().add(role);
	}

	@Override
	public User getUserByUserName(String username) {
		LOGGER.info("Fetching user by username");
		return userRepository.findByUsername(username)
						.orElseThrow(() -> new RuntimeException("No user exists with the user name: " + username));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
