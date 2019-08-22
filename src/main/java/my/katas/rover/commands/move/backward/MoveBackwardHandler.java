package my.katas.rover.commands.move.backward;

import static my.katas.rover.events.Events.roverMoved;
import static my.katas.rover.model.Location.location;

import com.google.common.eventbus.EventBus;

import lombok.AllArgsConstructor;
import my.katas.rover.commands.CommandHandler;
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
	public void handle(final MoveBackward command) {
		final Terrain terrain = terrains.findByName(command.getTerrain());
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Location location = location(command.getX(), command.getY());
		final Location newLocation = Rover.moveFrom(location, heading).backwardOn(terrain);
		eventBus.post(roverMoved(newLocation.getX(), newLocation.getY()));
	}
	
}