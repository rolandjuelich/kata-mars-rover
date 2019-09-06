package my.katas.rover.port.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import my.katas.rover.Commands;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class RoverControllerIntegrationTest {

	@Autowired
	ApplicationContext context;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Commands commandBus;

	@Autowired
	private TerrainRepository terrains;
	
	
	@Test
	public void test() throws Exception {
		given(terrains.findByName(anyString())).willReturn(new Terrain("Mars", 0,99,0,99));
		assertThat(context).isNotNull();
		assertThat(mockMvc).isNotNull();

		mockMvc.perform(get("/initialize"))
				.andDo(print())
				.andExpect(status().isOk());

		verify(commandBus).execute(any());
	}

}
