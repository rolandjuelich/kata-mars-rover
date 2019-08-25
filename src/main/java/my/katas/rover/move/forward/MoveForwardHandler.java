package my.katas.rover.move.forward;

import static my.katas.rover.move.Location.location;

import com.google.common.eventbus.Subscribe;

import lombok.AllArgsConstructor;
import my.katas.hexagonal.event.EventBus;
import my.katas.rover.Rover;
import my.katas.rover.command.RoverCommandHandler;
import my.katas.rover.event.RoverEvent;
import my.katas.rover.move.Location;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainRepository;
import my.katas.rover.turn.Heading;

@AllArgsConstructor
public class MoveForwardHandler implements RoverCommandHandler<MoveForward> {

	private final TerrainRepository terrains;
	private final EventBus eventBus;

	@Override
	@Subscribe
	public void handle(final MoveForward command) {
		final Terrain terrain = terrains.findByName(command.getTerrain());
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Location location = location(command.getX(), command.getY());
		final Location newLocation = Rover.moveFrom(location, heading).forwardOn(terrain);
		eventBus.post(RoverEvent.roverMoved(newLocation.getX(), newLocation.getY()));
	}
}