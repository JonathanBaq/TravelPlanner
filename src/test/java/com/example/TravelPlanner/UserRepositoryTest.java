package com.example.TravelPlanner;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.TravelPlanner.domain.User;
import com.example.TravelPlanner.domain.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	@Autowired
	private UserRepository urepository;

	@Test
	public void createNewUser() {
		User testuser = new User("testuser", "TESTUSER",
				"$2a$10$oJ8DDURGxcvJ1F7Xiu64IexaTBMZ02wTtf.1cybs/2u5C4PsM9QES");
		urepository.save(testuser);
		assertThat(testuser.getId()).isNotNull();
	}

	@Test
	public void findUserByRole() {
		User user = urepository.findByUsername("admin");
		assertThat(user.getRole()).isEqualTo("ADMIN");
	}

	@Test
	public void deleteUser() {
		User user = urepository.findByUsername("user");
		urepository.deleteById(user.getId());
		assertThat(urepository.count()).isEqualTo(2);
	}
}
