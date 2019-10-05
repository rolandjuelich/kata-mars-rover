package my.katas.rover.move.backward;

import static my.katas.rover.TestModel.randomRoverOn;
import static my.katas.rover.TestModel.randomTerrain;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.google.common.eventbus.EventBus;

import my.katas.rover.Rover;
import my.katas.rover.RoverCommands;
import my.katas.rover.RoverRepository;
import my.katas.rover.TestModel;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainRepository;

public class MoveBackwardHandlerUnitTest {
	@Rule
	public MockitoRule mockito = MockitoJUnit.rule();

	@Mock
	private RoverRepository rovers;

	@Mock
	private TerrainRepository terrains;

	@Mock
	private EventBus eventBus;

	@InjectMocks
	private MoveBackwardHandler handler;

	@Test
	public void shouldFindRoverById() {
		// given
		final Terrain terrain = randomTerrain();
		final Rover rover = randomRoverOn(terrain);

		given(rovers.findBy(rover.getId())).willReturn(rover);
		given(terrains.findById(terrain.getId())).willReturn(terrain);

		// when
		handler.handle(RoverCommands.moveBackward(rover.getId().getValue()));

		// then
		verify(rovers).findBy(rover.getId());
	}

	@Test
	public void shouldSaveRover() {
		// given
		final Terrain terrain = randomTerrain();
		final Rover rover = randomRoverOn(terrain);

		given(rovers.findBy(rover.getId())).willReturn(rover);
		given(terrains.findById(terrain.getId())).willReturn(terrain);

		// when
		handler.handle(RoverCommands.moveBackward(rover.getId().getValue()));

		// then
		verify(rovers).save(rover);
	}

	@Test
	public void shouldLoadTerrainOfStoredRover() {
		// given
		final Terrain terrain = randomTerrain();
		final Rover rover = randomRoverOn(terrain);

		given(rovers.findBy(rover.getId())).willReturn(rover);
		given(terrains.findById(terrain.getId())).willReturn(terrain);

		// when
		handler.handle(RoverCommands.moveBackward(rover.getId().getValue()));

		// then
		verify(terrains).findById(rover.getTerrainId());
	}

	@Test
	public void shouldFailIfRoverIsNotLoaded() {
		// given
		final String roverId = TestModel.randomRoverId().getValue();
		final MoveBackward moveBackward = RoverCommands.moveBackward(roverId);

		// when
		final Throwable throwable = catchThrowable(() -> handler.handle(moveBackward));

		// then
		assertThat(throwable).isInstanceOf(IllegalStateException.class).hasMessage("no rover found for id: " + roverId);
		verify(rovers, never()).save(Mockito.any());
		verify(eventBus, never()).post(Mockito.any());
	}
}
