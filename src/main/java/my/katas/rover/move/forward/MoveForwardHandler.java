package my.katas.rover.move.forward;

import static my.katas.rover.move.Location.location;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.RequiredArgsConstructor;
import my.katas.rover.Events;
import my.katas.rover.Rover;
import my.katas.rover.move.Location;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainRepository;
import my.katas.rover.turn.Heading;

@Component
@RequiredArgsConstructor
public class MoveForwardHandler {

	private final TerrainRepository terrains;
	private final EventBus eventBus;

	@Subscribe
	public void handle(final MoveForward command) {
		final Terrain terrain = terrains.findByName(command.getTerrain());
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Location location = location(command.getX(), command.getY());
		final Location newLocation = Rover.moveFrom(location, heading).forwardOn(terrain);
		eventBus.post(Events.roverMoved(newLocation.getX(), newLocation.getY()));
	}
}