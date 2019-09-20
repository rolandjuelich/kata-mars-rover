package my.katas.rover.move.forward;

import static my.katas.rover.TestModel.randomRoverOn;
import static my.katas.rover.TestModel.randomTerrain;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.google.common.eventbus.EventBus;

import my.katas.rover.Rover;
import my.katas.rover.RoverCommands;
import my.katas.rover.RoverRepository;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainRepository;

public class MoveForwardHandlerUnitTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule();

	@Mock
	private RoverRepository rovers;

	@Mock
	private TerrainRepository terrains;

	@Mock
	private EventBus eventBus;

	@InjectMocks
	private MoveForwardHandler handler;

	@Test
	public void shouldLoadRover() {
		// given
		final Terrain terrain = randomTerrain();
		final Rover rover = randomRoverOn(terrain);

		given(rovers.load()).willReturn(rover);
		given(terrains.findByName(terrain.getName())).willReturn(terrain);

		// when
		handler.handle(RoverCommands.moveForward());

		// then
		verify(rovers).load();
	}

	@Test
	public void shouldSaveRover() {
		// given
		final Terrain terrain = randomTerrain();
		final Rover rover = randomRoverOn(terrain);

		given(rovers.load()).willReturn(rover);
		given(terrains.findByName(terrain.getName())).willReturn(terrain);

		// when
		handler.handle(RoverCommands.moveForward());

		// then
		verify(rovers).save(rover);
	}

	@Test
	public void shouldLoadTerrainOfStoredRover() {
		// given
		final Terrain terrain = randomTerrain();
		final Rover rover = randomRoverOn(terrain);

		given(rovers.load()).willReturn(rover);
		given(terrains.findByName(terrain.getName())).willReturn(terrain);

		// when
		handler.handle(RoverCommands.moveForward());

		// then
		verify(terrains).findByName(rover.getTerrain());
	}

	@Test
	public void shouldDoNothingIfRoverIsNotLoaded() {
		// given
		given(rovers.load()).willReturn(null);

		// when
		handler.handle(mock(MoveForward.class));

		// then
		verify(rovers, never()).save(any());
		verifyZeroInteractions(terrains, eventBus);
	}

}
