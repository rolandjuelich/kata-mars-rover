package my.katas.rover.port.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
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

import my.katas.rover.Rover;
import my.katas.rover.RoverId;
import my.katas.rover.RoverRepository;
import my.katas.rover.TestModel;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class RoverControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TerrainRepository terrains;

	@Autowired
	private RoverRepository rovers;

	@Test
	public void shouldInitialize() throws Exception {
		// given
		final Terrain terrain = TestModel.randomTerrain();
		final MockHttpServletRequestBuilder request = get("/initialize?terrain=" + terrain.getId().getValue());
		final RoverId roverId = TestModel.randomRoverId();

		given(rovers.nextId()).willReturn(roverId);
		given(terrains.findById(terrain.getId())).willReturn(terrain);

		// when
		final MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(OK.value());
		assertThat(response.getContentAsString()).startsWith("RoverInitialized");
		assertThat(response.getContentAsString()).contains("id=" + roverId.getValue());
	}

	@Test
	public void shouldMoveForward() throws Exception {
		// given
		final Terrain terrain = TestModel.randomTerrain();
		final Rover rover = TestModel.randomRoverOn(terrain);
		final MockHttpServletRequestBuilder request = get("/forward?rover=" + rover.getId().getValue());
		
		given(rovers.findBy(rover.getId())).willReturn(rover);
		given(terrains.findById(rover.getTerrainId())).willReturn(terrain);

		// when
		final MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(OK.value());
		assertThat(response.getContentAsString()).startsWith("RoverMoved");
	}

	@Test
	public void shouldMoveBackward() throws Exception {
		// given
		final Terrain terrain = TestModel.randomTerrain();
		final Rover rover = TestModel.randomRoverOn(terrain);
		final MockHttpServletRequestBuilder request = get("/backward?rover=" + rover.getId().getValue());

		given(rovers.findBy(rover.getId())).willReturn(rover);
		given(terrains.findById(rover.getTerrainId())).willReturn(terrain);

		// when
		final MockHttpServletResponse response = mvc.perform(request).andReturn().getResponse();

		// then
		assertThat(response.getStatus()).isEqualTo(OK.value());
		assertThat(response.getContentAsString()).startsWith("RoverMoved");
	}

}
