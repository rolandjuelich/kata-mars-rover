package my.katas.rover.turn.right;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.RequiredArgsConstructor;
import my.katas.rover.Events;
import my.katas.rover.Rover;
import my.katas.rover.turn.Heading;

@Component
@RequiredArgsConstructor
public class TurnRightHandler {

	private final EventBus eventBus;

	@Subscribe
	public void handle(final TurnRight command) {
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Heading newHeading = Rover.turnFrom(heading).right();
		eventBus.post(Events.roverTurned(newHeading.name()));
	}
}