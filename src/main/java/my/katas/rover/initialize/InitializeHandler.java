package my.katas.rover.initialize;

import static my.katas.rover.Rover.initialize;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.RequiredArgsConstructor;
import my.katas.rover.Rover;

@Component
@RequiredArgsConstructor
public class InitializeHandler {

	private final EventBus eventBus;

	@Subscribe
	public void handle(final InitializeRover command) {
		final Rover rover = initialize(command.getX(), command.getY(), command.getHeading());
		if (rover != null) {
			eventBus.post(new RoverInitialized(rover.getLocation().getX(), rover.getLocation().getY(),
					rover.getHeading().name()));
		}
	}
}