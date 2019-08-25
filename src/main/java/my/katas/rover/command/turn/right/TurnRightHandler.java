package my.katas.rover.command.turn.right;

import static my.katas.rover.event.Event.roverTurned;

import com.google.common.eventbus.Subscribe;

import lombok.AllArgsConstructor;
import my.katas.rover.command.CommandHandler;
import my.katas.rover.event.EventBus;
import my.katas.rover.model.Heading;
import my.katas.rover.model.Rover;

@AllArgsConstructor
public class TurnRightHandler implements CommandHandler<TurnRight> {

	private final EventBus eventBus;

	@Override
	@Subscribe
	public void handle(final TurnRight command) {
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Heading newHeading = Rover.turnFrom(heading).right();
		eventBus.post(roverTurned(newHeading.name()));
	}
}