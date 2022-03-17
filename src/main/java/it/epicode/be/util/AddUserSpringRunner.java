package it.epicode.be.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.epicode.be.model.security.Role;
import it.epicode.be.model.security.Roles;
import it.epicode.be.model.security.User;
import it.epicode.be.repository.security.RoleRepository;
import it.epicode.be.repository.security.UserRepository;

@Component
public class AddUserSpringRunner implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.findAll().size()== 0) {
			BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

			Role roleA = new Role();
			roleA.setRoleName(Roles.ROLE_ADMIN);
			roleRepository.save(roleA);

			Role roleU = new Role();
			roleU.setRoleName(Roles.ROLE_USER);
			roleRepository.save(roleU);

			// ***** ADMIN *****

			User userA = new User();
			userA.setUserName("admin");
			userA.setPassword(bCrypt.encode("admin"));
			userA.setEmail("admin@email.it");
			userA.setActive(true);
			Set<Role> rolesA = new HashSet<>();
			rolesA.add(roleA);
			rolesA.add(roleU);
			userA.setRoles(rolesA);
			userRepository.save(userA);

			// ****** USER ******
			User userU = new User();
			userU.setUserName("user");
			userU.setPassword(bCrypt.encode("user"));
			userU.setEmail("user@email.it");
			userU.setActive(true);
			Set<Role> rolesU = new HashSet<>();
			rolesU.add(roleU);
			userU.setRoles(rolesU);
			userRepository.save(userU);

		}
		
	}

}
