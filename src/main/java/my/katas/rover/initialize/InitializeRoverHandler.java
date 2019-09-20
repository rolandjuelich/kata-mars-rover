package my.katas.rover.initialize;

import static my.katas.rover.RoverEvents.initializedAt;
import static my.katas.rover.move.Location.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.RequiredArgsConstructor;
import my.katas.rover.Rover;
import my.katas.rover.RoverRepository;
import my.katas.rover.move.Location;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainRepository;
import my.katas.rover.turn.Heading;

@Component
@RequiredArgsConstructor
public class InitializeRoverHandler {

	@Autowired
	private TerrainRepository terrains;

	@Autowired
	private RoverRepository rovers;

	@Autowired
	private EventBus eventBus;

	@Subscribe
	public void handle(final InitializeRover command) {
		final Terrain terrain = terrains.findByName(command.getTerrain());
		final Location location = location(command.getX(), command.getY());
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Rover rover = Rover.initialize(terrain, location, heading);
		if (rover != null) {
			rovers.save(rover);
			eventBus.post(
					initializedAt(rover.getLocation().getX(), rover.getLocation().getY(), rover.getHeading().name()));
		}
	}
}