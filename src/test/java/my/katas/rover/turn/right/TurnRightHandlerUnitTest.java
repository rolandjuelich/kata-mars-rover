package my.katas.rover.turn.right;

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
	public void shouldFindRoverById() {
		// given
		final Rover rover = randomRoverOn(randomTerrain());
		final TurnRight turnRight = RoverCommands.turnRight(rover.getId().getValue());

		given(rovers.findBy(rover.getId())).willReturn(rover);

		// when
		handler.handle(turnRight);

		// then
		verify(rovers).findBy(rover.getId());
	}

	@Test
	public void shouldSaveRover() {
		// given
		final Rover rover = randomRoverOn(randomTerrain());
		final TurnRight turnRight = RoverCommands.turnRight(rover.getId().getValue());

		given(rovers.findBy(rover.getId())).willReturn(rover);

		// when
		handler.handle(turnRight);

		// then
		verify(rovers).save(rover);
	}

	@Test
	public void shouldFailIfRoverIsNotLoaded() {
		// given
		final String roverId = TestModel.randomRoverId().getValue();
		final TurnRight turnRight = RoverCommands.turnRight(roverId);

		// when
		final Throwable throwable = catchThrowable(() -> handler.handle(turnRight));

		// then
		assertThat(throwable).isInstanceOf(IllegalStateException.class).hasMessage("no rover found for id: " + roverId);
		verify(rovers, never()).save(Mockito.any());
		verify(eventBus, never()).post(Mockito.any());
	}

}
