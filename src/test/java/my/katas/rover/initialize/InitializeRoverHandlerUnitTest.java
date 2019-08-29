package my.katas.rover.initialize;

import static org.assertj.core.api.Assertions.assertThat;
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
	public void shouldLoadTerrain() {
		// given
		final Terrain terrain = TestModel.randomTerrain();
		given(terrains.findByName(terrain.getName())).willReturn(terrain);

		// when
		handler.handle(new InitializeRover(terrain.getName(), 0, 0, "north"));

		// then
		verify(terrains).findByName(terrain.getName());
	}

	@Test
	public void shouldSaveRover() {
		// given
		final Terrain terrain = TestModel.randomTerrain();
		final Location location = TestModel.randomLocation();
		final Heading heading = TestModel.randomHeading();
		final Rover expected = Rover.initialize(terrain, location, heading);

		given(terrains.findByName(terrain.getName())).willReturn(terrain);

		// when
		handler.handle(new InitializeRover(terrain.getName(), location.getX(), location.getY(), heading.name()));
		final Rover actual = savedTo(rovers);

		// then
		assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
	}

	private static Rover savedTo(final RoverRepository rovers) {
		final ArgumentCaptor<Rover> captor = forClass(Rover.class);
		verify(rovers).save(captor.capture());
		return captor.getValue();
	}

}
