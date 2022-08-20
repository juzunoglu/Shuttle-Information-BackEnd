package com.alihan.uzunoglu.twilio;

import com.alihan.uzunoglu.twilio.entity.Role;
import com.alihan.uzunoglu.twilio.entity.User;
import com.alihan.uzunoglu.twilio.repository.RoleRepository;
import com.alihan.uzunoglu.twilio.repository.UserRepository;
import com.alihan.uzunoglu.twilio.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class TwilioApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(TwilioApplication.class);

    private final RoleRepository roleRepository; //todo make it a role service, only unique names can be entered!

    private final UserRepository userRepository;

    @Autowired
    public TwilioApplication(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        LOGGER.info("Starting the Application");
        SpringApplication.run(TwilioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findAll().size() < 1) {
            roleRepository.save(new Role("SUPER_ADMIN"));
            roleRepository.save(new Role("ROLE_ADMIN"));
            roleRepository.save(new Role("ROLE_DRIVER"));
            roleRepository.save(new Role("ROLE_PASSENGER"));
        }

        List<Role> savedRoles = roleRepository.findAll();
        LOGGER.info("Saved roles are: {}", savedRoles);
    }

}
