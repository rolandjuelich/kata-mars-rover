package my.katas.rover;

import static my.katas.rover.Location.location;
import static my.katas.rover.events.Events.roverMoved;
import static my.katas.rover.events.Events.roverTurned;

import my.katas.rover.commands.MoveBackward;
import my.katas.rover.commands.MoveForward;
import my.katas.rover.commands.TurnLeft;
import my.katas.rover.commands.TurnRight;
import my.katas.rover.events.EventBus;
import my.katas.rover.terrain.Terrain;
import my.katas.rover.terrain.TerrainRepository;

public class Application {

	private final EventBus eventBus;
	private final TerrainRepository terrains;

	public Application(final EventBus eventBus, final TerrainRepository terrains) {
		this.eventBus = eventBus;
		this.terrains = terrains;
	}

	public void handle(final MoveForward command) {
		final Terrain terrain = terrains.findByName(command.getTerrain());
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Rover rover = new Rover(location(command.getX(), command.getY()), heading);
		final Location newLocation = rover.moveForwardOn(terrain);
		eventBus.publish(roverMoved(newLocation.getX(), newLocation.getY()));
	}

	public void handle(final MoveBackward command) {
		final Terrain terrain = terrains.findByName(command.getTerrain());
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Rover rover = new Rover(location(command.getX(), command.getY()), heading);
		final Location newLocation = rover.moveBackwardOn(terrain);
		eventBus.publish(roverMoved(newLocation.getX(), newLocation.getY()));
	}

	public void handle(final TurnLeft command) {
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Rover rover = new Rover(heading);
		final Heading newHeading = rover.turnLeft();
		eventBus.publish(roverTurned(newHeading.name()));
	}

	public void handle(final TurnRight command) {
		final Heading heading = Heading.valueOf(command.getHeading().toUpperCase());
		final Rover rover = new Rover(heading);
		final Heading newHeading = rover.turnRight();
		eventBus.publish(roverTurned(newHeading.name()));
	}

}
