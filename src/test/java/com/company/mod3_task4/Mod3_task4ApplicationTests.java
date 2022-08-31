package com.company.mod3_task4;

import com.company.mod3_task4.entity.User;
import com.company.mod3_task4.security.DatabaseUserRepository;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.security.SystemAuthenticator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class Mod3_task4ApplicationTests {

	@Autowired
	SystemAuthenticator authenticator;
	@Autowired
	CurrentAuthentication currentAuthentication;
	@Autowired
	DatabaseUserRepository 	databaseUserRepository;
	@BeforeEach
	void setUp() {
		authenticator.begin();
	}

	@Test
	void contextLoads() throws Exception{
		authenticator.withSystem(() -> {
			UserDetails user = currentAuthentication.getUser();
			//System.out.println("User: " + user.getUsername()); // system
			if (Objects.equals(user.getUsername(), databaseUserRepository.getSystemUser().getUsername())){
				System.out.println("Done");
				return "Done";
			};
			// ...

			return null;
		});

	}


	@AfterEach
	void tearDown() {
		authenticator.end();  }


}
