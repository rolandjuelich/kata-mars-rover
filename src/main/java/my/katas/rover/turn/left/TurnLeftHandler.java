package my.katas.rover.turn.left;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.RequiredArgsConstructor;
import my.katas.rover.Events;
import my.katas.rover.Rover;
import my.katas.rover.turn.Heading;

@Component
@RequiredArgsConstructor
public class TurnLeftHandler {

	private final EventBus eventBus;

	@Subscribe
	public void handle(final TurnLeft command) {
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Heading newHeading = Rover.turnFrom(heading).left();
		eventBus.post(Events.roverTurned(newHeading.name()));
	}
}