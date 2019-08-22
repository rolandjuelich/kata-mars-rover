package my.katas.rover.commands.turnleft;

import static my.katas.rover.events.Events.roverTurned;

import com.google.common.eventbus.EventBus;

import lombok.AllArgsConstructor;
import my.katas.rover.commands.CommandHandler;
import my.katas.rover.model.Heading;
import my.katas.rover.model.Rover;

@AllArgsConstructor
public class TurnLeftHandler implements CommandHandler<TurnLeft> {

	private final EventBus eventBus;

	@Override
	public void handle(final TurnLeft command) {
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Heading newHeading = Rover.turnFrom(heading).left();
		eventBus.post(roverTurned(newHeading.name()));
	}
}