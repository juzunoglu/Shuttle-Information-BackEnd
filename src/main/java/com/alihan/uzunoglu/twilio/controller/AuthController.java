package com.alihan.uzunoglu.twilio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alihan.uzunoglu.twilio.entity.Role;
import com.alihan.uzunoglu.twilio.entity.User;
import com.alihan.uzunoglu.twilio.repository.RoleRepository;
import com.alihan.uzunoglu.twilio.repository.UserRepository;
import com.alihan.uzunoglu.twilio.security.jwt.JwtUtils;
import com.alihan.uzunoglu.twilio.security.payload.request.SignInRequest;
import com.alihan.uzunoglu.twilio.security.payload.request.SignUpRequest;
import com.alihan.uzunoglu.twilio.security.payload.response.JwtResponse;
import com.alihan.uzunoglu.twilio.security.payload.response.MessageResponse;
import com.alihan.uzunoglu.twilio.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder encoder;
	private final JwtUtils jwtUtils;

	public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
	}

	@PostMapping("/signIn")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody SignInRequest signInRequest) {
		Authentication authentication = authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(signInRequest.username(), signInRequest.password()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		LOGGER.info("SignIn Request is: {}", signInRequest);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt,
						userDetails.getId(),
						userDetails.getUsername(),
						userDetails.getEmail(),
						roles));
	}

	@PostMapping("/signUp")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignUpRequest signUpRequest) {
		LOGGER.info("SignUp Request is: {}", signUpRequest);
		if (userRepository.existsByUsername(signUpRequest.username())) {
			return ResponseEntity
							.badRequest()
							.body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(signUpRequest.email())) {
			return ResponseEntity
							.badRequest()
							.body(new MessageResponse("Error: Email is already in use!"));
		}
		// Create a new user account
		List<Role> rolesList = new ArrayList<>();

		List<Role> roles = new ArrayList<>();
		if (signUpRequest.role() == null || signUpRequest.role().isEmpty()) {
			Role userRole = roleRepository.findByName("ROLE_USER")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		}
		else {
			signUpRequest.role().forEach(role -> rolesList.add(new Role(role)));
		}
		User user = new User(signUpRequest.name(),
						signUpRequest.username(),
						signUpRequest.email(),
						encoder.encode(signUpRequest.password()),
						rolesList
		);
		rolesList.forEach(role -> {
			switch (role.getName()) {
				case "ROLE_ADMIN" -> {
					Role adminRole = roleRepository.findByName("ROLE_ADMIN")
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
				}
				case "ROLE_MODERATOR" -> {
					Role modRole = roleRepository.findByName("ROLE_MODERATOR")
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);
				}
				default -> {
					Role userRole = roleRepository.findByName("ROLE_USER")
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			}
		});
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
