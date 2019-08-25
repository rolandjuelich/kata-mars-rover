package my.katas.rover.command.turn.left;

import static my.katas.rover.event.Event.roverTurned;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.AllArgsConstructor;
import my.katas.rover.command.CommandHandler;
import my.katas.rover.model.Heading;
import my.katas.rover.model.Rover;

@AllArgsConstructor
public class TurnLeftHandler implements CommandHandler<TurnLeft> {

	private final EventBus eventBus;

	@Override
	@Subscribe
	public void handle(final TurnLeft command) {
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Heading newHeading = Rover.turnFrom(heading).left();
		eventBus.post(roverTurned(newHeading.name()));
	}
}