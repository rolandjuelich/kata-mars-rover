package my.katas.rover.turn.right;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.RequiredArgsConstructor;
import my.katas.rover.Rover;
import my.katas.rover.RoverEvents;
import my.katas.rover.RoverId;
import my.katas.rover.RoverRepository;
import my.katas.rover.turn.Heading;
import my.katas.rover.turn.RoverTurned;

@Component
@RequiredArgsConstructor
public class TurnRightHandler {

	private final RoverRepository rovers;

	private final EventBus eventBus;

	@Subscribe
	public void handle(final TurnRight command) {
		final Rover rover = rovers.findBy(new RoverId(command.getRoverId()));
		if (rover == null)
			throw new IllegalStateException("no rover found for id: " + command.getRoverId());

		rover.right();
		rovers.save(rover);

		final Heading heading = rover.getHeading();
		final RoverTurned roverTurned = RoverEvents.turnedTo(heading.name());
		eventBus.post(roverTurned);
	}
}