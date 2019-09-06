package my.katas.rover.port.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import my.katas.rover.Commands;
import my.katas.rover.initialize.InitializeRover;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class RoverControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private Commands commandBus;

	@Test
	public void test() throws Exception {

		mvc.perform(get("/initialize"))
				.andDo(print())
				.andExpect(status().isOk());

		verify(commandBus).execute(any(InitializeRover.class));
	}

}
