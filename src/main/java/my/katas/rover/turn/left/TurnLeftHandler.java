package my.katas.rover.turn.left;

import com.google.common.eventbus.Subscribe;

import lombok.AllArgsConstructor;
import my.katas.hexagonal.event.EventBus;
import my.katas.rover.Rover;
import my.katas.rover.command.RoverCommandHandler;
import my.katas.rover.event.RoverEvent;
import my.katas.rover.turn.Heading;

@AllArgsConstructor
public class TurnLeftHandler implements RoverCommandHandler<TurnLeft> {

	private final EventBus eventBus;

	@Override
	@Subscribe
	public void handle(final TurnLeft command) {
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Heading newHeading = Rover.turnFrom(heading).left();
		eventBus.post(RoverEvent.roverTurned(newHeading.name()));
	}
}