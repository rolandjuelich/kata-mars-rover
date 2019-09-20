package my.katas.rover.turn.right;

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

public class TurnRightHandlerUnitTest {
	
	@Rule
	public MockitoRule mockito = MockitoJUnit.rule();

	@Mock
	private RoverRepository rovers;

	@Mock
	private EventBus eventBus;

	@InjectMocks
	private TurnRightHandler handler;

	@Test
	public void shouldLoadRover() {
		// given
		given(rovers.load()).willReturn(randomRoverOn(randomTerrain()));

		// when
		handler.handle(RoverCommands.turnRight());

		// then
		verify(rovers).load();
	}

	@Test
	public void shouldSaveRover() {
		// given
		final Rover rover = randomRoverOn(randomTerrain());
		given(rovers.load()).willReturn(rover);

		// when
		handler.handle(RoverCommands.turnRight());

		// then
		verify(rovers).save(rover);
	}

	@Test
	public void shouldDoNothingIfRoverIsNotLoaded() {
		// given
		given(rovers.load()).willReturn(null);

		// when
		handler.handle(mock(TurnRight.class));

		// then
		verify(rovers, never()).save(any());
		verifyZeroInteractions(eventBus);
	}

}
