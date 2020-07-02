package com.example.TODO;

import com.example.TODO.controller.UserController;
import com.example.TODO.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(value = UserController.class)
class TodoApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	UserRepository userRepository;

	@Test
	public  void contextLoads() throws  Exception {

		Mockito.when(userRepository.findAll()).thenReturn(
				Collections.emptyList()
		);

		MvcResult mvcResult=mockMvc.perform(
				MockMvcRequestBuilders.get("/users")
				.accept(MediaType.APPLICATION_JSON)
		).andReturn();

		Mockito.verify(userRepository).findAll();
	}

}
