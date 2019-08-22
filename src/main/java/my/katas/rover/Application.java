package my.katas.rover;

import my.katas.rover.commands.backward.MoveBackward;
import my.katas.rover.commands.backward.MoveBackwardHandler;
import my.katas.rover.commands.forward.MoveForward;
import my.katas.rover.commands.forward.MoveForwardHandler;
import my.katas.rover.commands.turnleft.TurnLeft;
import my.katas.rover.commands.turnleft.TurnLeftHandler;
import my.katas.rover.commands.turnright.TurnRight;
import my.katas.rover.commands.turnright.TurnRightHandler;
import my.katas.rover.events.EventBus;
import my.katas.rover.model.terrain.TerrainRepository;

public class Application {

	private final EventBus eventBus;
	private final TerrainRepository terrains;

	public Application(final EventBus eventBus, final TerrainRepository terrains) {
		this.eventBus = eventBus;
		this.terrains = terrains;
	}

	public void handle(final MoveForward command) {
		new MoveForwardHandler(eventBus, terrains).handle(command);
	}

	public void handle(final MoveBackward command) {
		new MoveBackwardHandler(eventBus, terrains).handle(command);
	}

	public void handle(final TurnLeft command) {
		new TurnLeftHandler(eventBus).handle(command);
	}

	public void handle(final TurnRight command) {
		new TurnRightHandler(eventBus).handle(command);
	}

}
