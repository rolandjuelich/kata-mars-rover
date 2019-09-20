package my.katas.rover.move.forward;

import static my.katas.rover.RoverEvents.movedTo;

import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.RequiredArgsConstructor;
import my.katas.rover.Rover;
import my.katas.rover.RoverRepository;
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
		final Rover rover = rovers.load();
		if (rover != null) {
			final Terrain terrain = terrains.findByName(rover.getTerrain());
			rover.forwardOn(terrain);
			rovers.save(rover);
			eventBus.post(movedTo(rover.getLocation().getX(), rover.getLocation().getY()));
		}
	}
}