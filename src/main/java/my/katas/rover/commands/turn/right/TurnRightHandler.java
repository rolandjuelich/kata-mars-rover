package my.katas.rover.commands.turn.right;

import static my.katas.rover.events.Events.roverTurned;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.AllArgsConstructor;
import my.katas.rover.commands.CommandHandler;
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