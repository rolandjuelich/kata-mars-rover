package my.katas.rover.move.forward;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.RequiredArgsConstructor;
import my.katas.rover.Rover;
import my.katas.rover.RoverEvents;
import my.katas.rover.RoverId;
import my.katas.rover.RoverRepository;
import my.katas.rover.move.Location;
import my.katas.rover.move.RoverMoved;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainRepository;

@Component
@RequiredArgsConstructor
public class MoveForwardHandler {

	private final TerrainRepository terrains;

	private final RoverRepository rovers;

	private final EventBus eventBus;

	@Subscribe
	public void handle(final MoveForward command) {
		final Rover rover = rovers.findBy(new RoverId(command.getRoverId()));
		if (rover == null) {
			throw new IllegalStateException("no rover found with id: " + command.getRoverId());
		}

		final Terrain terrain = terrains.findById(rover.getTerrainId());

		rover.forwardOn(terrain);
		rovers.save(rover);

		final Location location = rover.getLocation();
		final RoverMoved roverMoved = RoverEvents.movedTo(location.getX(), location.getY());
		eventBus.post(roverMoved);
	}
}