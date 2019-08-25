package my.katas.rover.command.move.backward;

import static my.katas.rover.event.Event.roverMoved;
import static my.katas.rover.model.Location.location;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import lombok.AllArgsConstructor;
import my.katas.rover.command.CommandHandler;
import my.katas.rover.model.Heading;
import my.katas.rover.model.Location;
import my.katas.rover.model.Rover;
import my.katas.rover.model.terrain.Terrain;
import my.katas.rover.model.terrain.TerrainRepository;

@AllArgsConstructor
public class MoveBackwardHandler implements CommandHandler<MoveBackward> {

	private final TerrainRepository terrains;
	private final EventBus eventBus;

	@Override
	@Subscribe
	public void handle(final MoveBackward command) {
		final Terrain terrain = terrains.findByName(command.getTerrain());
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Location location = location(command.getX(), command.getY());
		final Location newLocation = Rover.moveFrom(location, heading).backwardOn(terrain);
		eventBus.post(roverMoved(newLocation.getX(), newLocation.getY()));
	}
	
}