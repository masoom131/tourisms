package com.web.tourism;

import com.web.tourism.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebTourismApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void repositoryTest() {
		String className = userRepository.getClass().getName();
		String packageName =  userRepository.getClass().getPackageName();

		System.out.println("className:::: "+className+ "and packageName::::::: "+packageName);
	}

}
