package com.alihan.uzunoglu.twilio.repository;

import com.alihan.uzunoglu.twilio.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
