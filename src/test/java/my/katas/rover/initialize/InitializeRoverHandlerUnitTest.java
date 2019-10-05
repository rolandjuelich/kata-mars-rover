package my.katas.rover.initialize;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.google.common.eventbus.EventBus;

import my.katas.rover.Rover;
import my.katas.rover.RoverCommands;
import my.katas.rover.RoverId;
import my.katas.rover.RoverRepository;
import my.katas.rover.TestModel;
import my.katas.rover.move.Location;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainRepository;
import my.katas.rover.turn.Heading;

public class InitializeRoverHandlerUnitTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule();

	@Mock
	private TerrainRepository terrains;

	@Mock
	private RoverRepository rovers;

	@Mock
	private EventBus eventBus;

	@InjectMocks
	private InitializeRoverHandler handler;

	@Test
	public void shouldSaveRover() {
		// given
		final RoverId roverId = TestModel.randomRoverId();
		final Terrain terrain = TestModel.randomTerrain();
		final Location location = TestModel.randomLocation();
		final Heading heading = TestModel.randomHeading();
		
		given(terrains.findById(terrain.getId())).willReturn(terrain);
		given(rovers.nextId()).willReturn(roverId);
		
		// when
		handler.handle(
				new InitializeRover(terrain.getId().getValue(), location.getX(), location.getY(), heading.name()));
		final Rover actual = passedTo(rovers);

		// then
		assertThat(actual.getId()).isEqualTo(roverId);
		assertThat(actual.getTerrainId()).isEqualTo(terrain.getId());
		assertThat(actual.getHeading()).isEqualTo(heading);
		assertThat(actual.getLocation()).isEqualTo(location);
	}

	@Test
	public void shouldFailIfTerrainNotFound() {
		// given
		final String terrainId = TestModel.randomTerrain().getId().getValue();
		final InitializeRover initializeWithUnknownTerrain = RoverCommands.initialize(terrainId);

		// when
		final Throwable throwable = catchThrowable(() -> handler.handle(initializeWithUnknownTerrain));

		// then
		assertThat(throwable).isInstanceOf(IllegalStateException.class)
				.hasMessage("no terrain found for id: " + terrainId);
	}

	private static Rover passedTo(final RoverRepository rovers) {
		final ArgumentCaptor<Rover> captor = forClass(Rover.class);
		verify(rovers).save(captor.capture());
		return captor.getValue();
	}

}
