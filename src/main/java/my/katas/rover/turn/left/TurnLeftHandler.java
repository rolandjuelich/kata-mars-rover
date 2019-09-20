package my.katas.rover.turn.left;

import static my.katas.rover.RoverEvents.turnedTo;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.RequiredArgsConstructor;
import my.katas.rover.Rover;
import my.katas.rover.RoverRepository;

@Component
@RequiredArgsConstructor
public class TurnLeftHandler {

	private final RoverRepository rovers;

	private final EventBus eventBus;

	@Subscribe
	public void handle(final TurnLeft command) {
		final Rover rover = rovers.load();
		if (rover != null) {
			rover.left();
			rovers.save(rover);
			eventBus.post(turnedTo(rover.getHeading().name()));
		}
	}
}