package my.katas.rover.port.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class RoverControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void shouldInitialize() throws Exception {
		// given
		final MockHttpServletRequestBuilder request = get("/initialize");

		// when
		final MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(OK.value());
		assertThat(response.getContentAsString()).startsWith("RoverInitialized");
	}

	
	@Test
	public void shouldMoveForward() throws Exception {
		//given
		final MockHttpServletRequestBuilder request = get("/forward");

		// when
		final MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();
		
		// then
		assertThat(response.getStatus()).isEqualTo(OK.value());
		assertThat(response.getContentAsString()).startsWith("RoverMoved");
	}

}
