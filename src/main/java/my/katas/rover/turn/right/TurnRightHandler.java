package my.katas.rover.turn.right;

import com.google.common.eventbus.Subscribe;

import lombok.AllArgsConstructor;
import my.katas.hexagonal.event.EventBus;
import my.katas.rover.Rover;
import my.katas.rover.command.RoverCommandHandler;
import my.katas.rover.event.RoverEvent;
import my.katas.rover.turn.Heading;

@AllArgsConstructor
public class TurnRightHandler implements RoverCommandHandler<TurnRight> {

	private final EventBus eventBus;

	@Override
	@Subscribe
	public void handle(final TurnRight command) {
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Heading newHeading = Rover.turnFrom(heading).right();
		eventBus.post(RoverEvent.roverTurned(newHeading.name()));
	}
}