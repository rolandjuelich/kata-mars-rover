package my.katas.rover.initialize;

import static my.katas.rover.move.Location.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.RequiredArgsConstructor;
import my.katas.rover.Rover;
import my.katas.rover.RoverEvents;
import my.katas.rover.RoverId;
import my.katas.rover.RoverRepository;
import my.katas.rover.move.Location;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainId;
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

		final Terrain terrain = terrains.findById(new TerrainId(command.getTerrainId()));
		if (terrain == null) {
			throw new IllegalStateException("no terrain found for id: " + command.getTerrainId());
		}

		final RoverId id = rovers.nextId();
		final Location location = location(command.getX(), command.getY());
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Rover rover = new Rover(id, terrain.getId(), location, heading);
		rovers.save(rover);

		final RoverInitialized event = RoverEvents.initialized(rover.getId().getValue(), location.getX(),
				location.getY(),
				heading.name());
		eventBus.post(event);
	}
}